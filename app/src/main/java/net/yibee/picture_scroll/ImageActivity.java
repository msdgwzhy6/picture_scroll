package net.yibee.picture_scroll;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ImageActivity extends Activity {
    private ViewPager viewPager;
    private ArrayList<View> pageViews;
    private ImageView imageView;
    private ImageView im_pic;
    private ImageView viewflipper_two_iv_back;
    private TextView tvMessage;
    private TextView tv_title;
    private TextView tv_page;
    private ImageView[] imageViews;
    // 包裹滑动图片LinearLayout
    private ViewGroup main;
    private String[] titles; // 图片标题
    private int[] imageResId; // 图片ID
    private String title = "风景图片欣赏";
    private Handler mHandler;
    private Runnable mRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();

        imageResId = new int[]{R.drawable.pp1,
                R.drawable.pp2, R.drawable.pp3,
                R.drawable.pp4, R.drawable.pp5,
                R.drawable.pp3};
        titles = new String[imageResId.length];
        titles[0] = "随着Windows Phone逐步渗入中国智能手机市场，Windows 关注的视野，成了移动应用开发者继iOS和Android之外的另一选择。";
        titles[1] = "好友曾向我展示了最新的iPhone和iPad版《极品飞车》。游戏的渲染效果令人印象深刻，是款蓄势待发的优秀游戏。但是，游戏的前端是典型的UI设计偏差案例。但界面中有大量的属性数据等内容，它在玩家没有时间做决定时提供了过多的内容。这些内容能够显著改变他们的游戏体验，但却在玩家往往感受不到变化的时候呈现。";
        titles[2] = "3月31日，第四届CocoaChina游戏开发者大会暨Cocos2D-X技术研讨会在北京举行。来自全世界的移动互联网精英在此交流经验、相互学习。同时这次我们有幸也采访到了CocoaChina的CEO陈昊芝先生。他将为我们揭秘苹果新榜单的算法秘密，并未我们带来捕鱼达人成功的推广经验，对微软Windows Phone平台的吐槽和对未来3D技术趋势的前瞻。";
        titles[3] = "北京时间4月1日消息，谷歌虽然一直以来都在地图产品上提供和完善各种各样的系统接口，但是迄今为止却一直忽略了广受欢迎的NES游戏。然而也就是今天搜索巨头谷歌利用愚人节的机会，“调皮”地推出流行的NES八位地图产品。";
        titles[4] = "在NASA和美国国家地理的参与下，这群小鸟摆脱了地球引力，跑到了太空，这就是即将在2012年3月22日公测的《愤怒的小鸟：太空版》。为了让小鸟们成功解救出它们珍贵的“无价之蛋”，美国国家地理为此出版了一本书，来提供完成任务所需的关于行星、卫星、银河系等关于外太空的知识，并将于3月20日开始发售";
        titles[5] = "Google搜索引擎已经赢得全球范围的赞誉，而这一切都要归功于Google开创性的MapReduce。Google搜索引擎算法只是其搜索引擎的一部分，在后台支持Google算法的基础设施才是真正的幕后英雄，其基础设施可快速的索引链接成千上万台普通服务器。MapReduce的成功也直接推动了Hadoop的发展。";
        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        for (int i = 0; i < imageResId.length; i++) {
            View view = inflater.inflate(R.layout.image_item, null);
            im_pic = (ImageView) view.findViewById(R.id.im1_pic);
            im_pic.setBackgroundResource(imageResId[i]);
            pageViews.add(view);
        }
        main = (ViewGroup) inflater.inflate(R.layout.image_viewpager, null);
        viewPager = (ViewPager) main.findViewById(R.id.guidePages);
        viewflipper_two_iv_back = (ImageView) main.findViewById(R.id.viewflipper_two_iv_back);
        tv_title = (TextView) main.findViewById(R.id.tv_title);
        tv_page = (TextView) main.findViewById(R.id.tv_page);
        tvMessage = (TextView) main.findViewById(R.id.tv1_message);
        imageViews = new ImageView[pageViews.size()];
        setContentView(main);
        tv_title.setText(title);
        tv_page.setText((1) + "/" + titles.length);
        if(titles!=null&&titles.length>0){
            tvMessage.setText(titles[0]);
        }
        viewPager.setAdapter(new GuidePageAdapter());
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
        viewflipper_two_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    // 指引页面数据适配器
    class GuidePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(pageViews.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(pageViews.get(arg1));
            return pageViews.get(arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }

    // 指引页面更改事件监听器
    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            //Log.e("onPageScrollStateChanged",arg0+"");
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            //Log.e("onPageScrolled",arg0+":"+arg1+":"+arg2);

        }

        @Override
        public void onPageSelected(int arg0) {
            final int arg=arg0;
            new Thread() {
                public void run() {
                    mHandler.post(mRunnable);
                }
            }.start();
            mRunnable = new Runnable() {

                @Override
                public void run() {
                    tv_page.setText(arg+1+"/"+titles.length);
                    tv_title.setText(title);
                    if(arg<titles.length){
                      tvMessage.setText(titles[arg]);
                    }
                }
            };
        }
    }
}

