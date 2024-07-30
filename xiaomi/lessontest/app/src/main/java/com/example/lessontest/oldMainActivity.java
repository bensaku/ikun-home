package com.example.lessontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class oldMainActivity extends AppCompatActivity implements GameRecycleAdapter.OnItemClickListener {



    @Override
    public void onItemClick(View view, int position) {
        int id = view.getId();
        if (id == R.id.game_icon) {
            Toast.makeText(oldMainActivity.this,"点击了icon",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.game_button) {
            Toast.makeText(oldMainActivity.this,"点击了button",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(oldMainActivity.this,"item点击",Toast.LENGTH_SHORT).show();
        }

    }


    private void setData(List<GameBean> data) {
        for (int i = 0; i < 50; i++) {
            int[] icons = new int[]{R.drawable.shape2, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground};
            GameBean game = new GameBean();
            game.setGameIcon(icons[i % 3]);
            game.setGameName("游戏数据" + i);
            data.add(game);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_activity_main);

        /**
         * 加载网络图片
         * 基本形式 先with()，再load()，最后into()
         */
//        ImageView mImage = (ImageView)findViewById(R.id.test_image);
//        Glide.with(this).load(R.drawable.xiaomi).apply(RequestOptions.bitmapTransform(BlurTransformation(25,3))).into(mImage);
//

//        RecyclerView recyclerView = findViewById(R.id.demo_recycleview);
//        List<GameBean> data = new ArrayList<>();
//        setData(data);
//        GameRecycleAdapter adapter = new GameRecycleAdapter(data);
//
//        recyclerView.setAdapter(adapter);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        TextView textView = findViewById(R.id.main_text);
//        String str = "Hello World!Hello World!";
//        int bStarIndex = 8;
//        int bEndIndex = str.length();
//        int fStarIndex = 0;
//        int fEndIndex = 5;
//        int clickStartIndex = 0;
//        int clickEndIndex = str.length();
//
//        SpannableString spannableString =new SpannableString(str);
//        spannableString.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.blue)),bStarIndex,bEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.green)),fStarIndex,fEndIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(@NonNull View widget) {
//                Toast.makeText(MainActivity.this,"点击链接",Toast.LENGTH_SHORT).show();
//            }
//        },clickStartIndex,clickEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
//
//        Drawable drawable =getResources().getDrawable(R.drawable.ic_launcher_background);
//        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
//        spannableString.setSpan(new ImageSpan(drawable,ImageSpan.ALIGN_BASELINE),0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        textView.setText(spannableString);


//        SeekBar rotateBar = findViewById(R.id.set_rotate);
//        ImageView imageView =findViewById(R.id.rotate_image);
//        TextView rotateText=findViewById(R.id.rotate_text);
//
//        rotateBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                imageView.setPivotX(imageView.getWidth() / 2);
//                imageView.setPivotY(imageView.getHeight() / 2); //设置支点在图片中心
//                float alpha = progress / 360.0f;
//                imageView.setRotation(progress);
//                imageView.setAlpha(alpha);
//                rotateText.setText("设置旋转角度:" + progress + "设置透明度:" + alpha);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                // 在这里添加你需要在开始拖动SeekBar时执行的代码
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                // 在这里添加你需要在停止拖动SeekBar时执行的代码
//            }
//        });


//        TextView button = findViewById(R.id.btn);
//        button.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction()==MotionEvent.ACTION_DOWN){
//                    button.setBackground(getResources().getDrawable(R.drawable.shape2));
//                } else if (event.getAction()==MotionEvent.ACTION_UP) {
//                    button.setBackground(getResources().getDrawable(R.drawable.shape));
//
//                }
//                return false;
//            }
//        });


    }
}