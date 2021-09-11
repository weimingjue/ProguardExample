package com.wang.proguard.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.li.test.LiLi;
import com.www.ww2.WwwTest;

/**
 * bean目录会被保留
 * com.li的包下都会保留
 * <p>
 * Activity名会保留
 * 所有第三方代码都会被保留，如引用的fastjson
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 变量名会被混淆
     */
    private WebView mWv;
    private TestC mTestC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        proguardTest();
        mWv = new WebView(this);
        mWv.setWebChromeClient(new WebTest());
        mTestC = new TestC();

        System.out.println(LiLi.mName);
        System.out.println("www类被混淆：" + WwwTest.wt + "，类名：" + WwwTest.class);

        //反射被混淆的类会报错
        try {
            Class<?> test = Class.forName(getPackageName() + ".activity.MainActivity$TestC");
//            Class<?> test = Class.forName("com.wang.proguard.activity.MainActivity$TestC");//如果是直接拼的话会被编译器自动优化修正
            Log.e("混淆", "不会提示出来" + test.getName());
        } catch (ClassNotFoundException e) {
            Log.e("混淆", "被混淆了用反射会报错");
        }

        //反射没被混淆的类正常
        try {
            Class<?> test = Class.forName(getPackageName() + ".activity.MainActivity$TestClass2");
            Log.e("混淆", "完全正常" + test.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e("混淆", "没被混淆，不会提示这句");
        }
    }

    /**
     * 普通的方法名会被混淆
     */
    public void proguardTest() {
    }

    /**
     * 未引用会被删除
     */
    public void proguardTest3() {
    }

    /**
     * 普通class会被混淆
     */
    public static class TestC {
    }

    /**
     * keep注解会保留所有属性及方法（内部类无法保留）
     */
    @Keep
    public static class TestClass2 extends TestC {
        public String testC = "c";
        public TestClassChild tcc = new TestClassChild();

        public void testFC() {
        }

        /**
         * 由于内部类是被打包出去的，所以依然会被混淆
         */
        public static class TestClassChild {
        }
    }

    /**
     * 名字会被混淆，方法和变量会被保留
     */
    public static class WebTest extends WebChromeClient {
        public int testW = 1;

        public void testW() {
            System.out.println("哈哈");
        }
    }

    /**
     * 所有的view及构造函数都会被保留
     */
    public class TestView extends android.support.v7.widget.AppCompatTextView {

        /**
         * 成员变量会被混淆
         */
        private Context mContext;

        /**
         * view的构造方法无论有没有用过都会保留
         */
        public TestView(Context context) {
            super(context);
        }

        public TestView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            mContext = context;
        }
    }

    /**
     * 注解为h5调用的会被保留
     */
    @JavascriptInterface
    public void jsData() {

    }
}
