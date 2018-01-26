# UniversalAdapter

Mostly from [UniversalAdapter](https://github.com/Raizlabs/UniversalAdapter),thanks a lot.

gradle:
```
allprojects {
    repositories {
         jcenter()
    }
}
```
```
compile 'com.llj.adapter:lib-universalAdapter:1.0.6'

```


- remove the dependence library [CoreUtils](https://github.com/Raizlabs/CoreUtils)
- use SparseArray to storage mHeaderHolders，mFooterHolders，mItemLayouts，so you can use non-continuous
  value fo viewType
- add a class ViewHolderHelper,so you can use this to replace original ViewHolder。Use this method,
you only need to use addItemLayout to bind layout and implement onBindViewHolder.

```java
PhotoPreviewAdapter(List<RepeatSampleImage> list) {
            super(list);
            addItemLayout(R.layout.emotion_pic_repeat_filter_pager_item);
        }
```

```java
@Override
        protected void onBindViewHolder(ViewHolderHelper viewHolder, RepeatSampleImage repeatSampleImage, final int position) {
            viewHolder.itemView.getLayoutParams().width = mWidth;
            viewHolder.itemView.getLayoutParams().height = -1;

            final SimpleDraweeView zoomableDraweeView = viewHolder.getView(R.id.zoomable);
            if (repeatSampleImage != null && !TextUtils.isEmpty(repeatSampleImage.getOriginPath())) {

                viewHolder.setVisibility(R.id.select_tv, repeatSampleImage.isChecked() ? View.VISIBLE : View.GONE);

                zoomableDraweeView.setOnClickListener(new OnMyClickListener() {
                    @Override
                    public void onCanClick(View v) {
                        mIsTouchedInViewPager = false;
                        mRepeatPhotoThumbRv.smoothScrollToPosition(position);
                    }
                });

                int[] size = BitmapUtilLj.getBitmapFileSize(repeatSampleImage.getOriginPath());
                //图片的宽高
                int imageWidth = size[0];
                int imageHeight = size[1];
                //将图片的宽高缩放至屏幕的宽度,比例不变
                int width = BaseApplication.DISPLAY_WIDTH;
                int height = (int) (width * imageHeight / (imageWidth * 1.0f));
                FrescoUtil.setController(repeatSampleImage.getOriginPath(), width, height, true, zoomableDraweeView, new BaseControllerListener() {
                    @Override
                    public void onFinalImageSet(String id, @Nullable Object imageInfo, @Nullable Animatable animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);
                    }
                });
            }
        }
```


also you can use original method:

```java
@Override
        protected ViewHolderWrap onCreateViewHolder(ViewGroup parent, int itemType) {
            switch (itemType) {
                case ListBasedAdapterWrap.VIEW_TYPE_LOADING:
                    return new ViewHolderWrap(inflateView(parent, R.layout.reach_bottom_load_more_item));
                case MyMessage.TYPE_VIDEO:
                case MyMessage.TYPE_PHOTO:
                case MyMessage.TYPE_ALBUM:
                    return new DynamicHolder(mBaseFragmentActivity.getLayoutInflater().inflate(R.layout.dynamic_fragment_item, parent, false));
                case MyMessage.TYPE_PHOTO_REPLY:
                case MyMessage.TYPE_ALBUM_REPLY:
                    return new DynamicHolder(mBaseFragmentActivity.getLayoutInflater().inflate(R.layout.dynamic_fragment_item, parent, false));
                default:
                    return new DynamicHolder(mBaseFragmentActivity.getLayoutInflater().inflate(R.layout.dynamic_fragment_item, parent, false));
            }
        }
```

```java
@Override
        protected void onBindViewHolder(ViewHolderWrap viewHolder, final MyMessage dynamic, final int position) {
            if (dynamic != null) {
                switch (getItemViewType(position)) {
                    case MyMessage.TYPE_INVITE_TO_FAMILY:
                        InviteHolder inviteHolder = (InviteHolder) viewHolder;
                        break;
                    case MyMessage.TYPE_EXIT_FAMILY:
                        break;
                    case MyMessage.TYPE_VIDEO_LIKE:
                    case MyMessage.TYPE_PHOTO_LIKE:
                        break;
                }
            } else {
                setLoadingLayoutParams(viewHolder.itemView);
            }
        }
```

