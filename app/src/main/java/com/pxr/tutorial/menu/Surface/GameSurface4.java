package com.pxr.tutorial.menu.Surface;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pxr.tutorial.menu.Object.Ball;
import com.pxr.tutorial.menu.Object.GameTimer;
import com.pxr.tutorial.menu.Object.NumberBall;
import com.pxr.tutorial.menu.Object.Score;
import com.pxr.tutorial.menu.Object.Tiger;
import com.pxr.tutorial.menu.R;
import com.pxr.tutorial.menu.Thread.GameThread;
import com.pxr.tutorial.menu.Utility.CommonFeatures;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by danglph on 10/07/2017.
 */

public class GameSurface4 extends SurfaceView implements SurfaceHolder.Callback, IGameSurface {
    private static final String TAG = GameSurface4.class.getSimpleName();

    private long totalScore = 0;
    private Score score = null;
    private GameThread gameThread;
    private Tiger tiger = null;
    private Map<Integer, NumberBall> mapNumberBall = new HashMap<>();
    private static int numberLength = 100;
    private int[] numberArray = new int[numberLength];
    private int cR = 60;
    private int zoomBoard = 60;
    private int numberToFind = 1;
    private int numberIndexNext = 1;
    private boolean isNext =false;
//    private Ball[] arrayBall = null;
//    private Ball ball = null;
//    private Ball startBall = null;
//    private int t_l_Ball = -1;
//    private int t_r_Ball = -1;
//    private int b_l_Ball = -1;
//    private int b_r_Ball = -1;
    private Random rand = new Random();

    private Bitmap bmTiger = BitmapFactory.decodeResource(this.getResources(), R.drawable.tigertranp2);

//    private SELECTION typeSelection = SELECTION.TYPE1;
//    private float scaleValue = 0.4f;
//    private int ballWidth;
//    private int ballHeight;
//    private int subtime = 1;
    //    private long lastupdateNanoTime = -1;
    private GameTimer gameTimer = null;
//    private Bitmap bmBallRedDefaultSize = BitmapFactory.decodeResource(this.getResources(), R.drawable.ballred);
//    private Bitmap bmBallBlueDefaultSize = BitmapFactory.decodeResource(this.getResources(), R.drawable.ballblue);
//    private Bitmap bmBallGrayDefaultSize = BitmapFactory.decodeResource(this.getResources(), R.drawable.ballgray);
//    private Bitmap bmBallGreenDefaultSize = BitmapFactory.decodeResource(this.getResources(), R.drawable.ballgreen);
//    private Bitmap bmBallMagentaDefaultSize = BitmapFactory.decodeResource(this.getResources(), R.drawable.ballmagenta);
//    private Bitmap bmBallYellowDefaultSize = BitmapFactory.decodeResource(this.getResources(), R.drawable.ballyellow);

    public GameSurface4(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public GameSurface4(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        // Đảm bảo Game Surface có thể focus để điều khiển các sự kiện.
        this.setFocusable(true);

        // Sét đặt các sự kiện liên quan tới Game.
        this.getHolder().addCallback(this);
    }

    public GameSurface4(Context context) {
        super(context);

        // Đảm bảo Game Surface có thể focus để điều khiển các sự kiện.
        this.setFocusable(true);

        // Sét đặt các sự kiện liên quan tới Game.
        this.getHolder().addCallback(this);
    }

    private String loadGameData() {

        Activity context = ((Activity) (this.getContext()));

        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        try {
            totalScore = Long.parseLong(sharedPref.getString(this.getResources().getString(R.string.score), "0"));
            return sharedPref.getString(this.getResources().getString(R.string.board), "");

        } catch (Exception ex) {
            totalScore = 0;
            Log.e(TAG, ex.getMessage());
            return "";
        }
    }

    public void saveGameData() {
        saveData(false);
    }

    private void saveData(boolean isLosed) {
//        String boardStr = "";
//        if (isLosed) {
//            totalScore = 0;
//        } else {
//            boardStr = getDataBoard(this.numbcol, this.numbrow, this.arrayBall);
//        }
//        try {
//            Activity context = ((Activity) (this.getContext()));
//
//            SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
//
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putString(this.getResources().getString(R.string.score), totalScore + "");
//            editor.putString(this.getResources().getString(R.string.board), boardStr);
//            editor.commit();
//        } catch (Exception ex) {
//            Log.e(TAG, ex.getMessage());
//        }
    }

    private String getDataBoard(int numbcol, int numbrow, Ball[] arrayBall) {
        if (arrayBall != null) {
            StringBuilder boardStr = new StringBuilder();
            boardStr.append(numbcol);
            boardStr.append(CommonFeatures.splitCharactor_5);
            boardStr.append(numbrow);
            boardStr.append(CommonFeatures.splitCharactor_5);
            for (Ball ball : arrayBall) {
                boardStr.append(ball.getBallColor());
            }
            boardStr.append(CommonFeatures.splitCharactor_3);
            return boardStr.toString();
        } else {
            return "";
        }
    }

    private void initializeGame() {
//        String databoard = loadGameData();
//        score = new Score(null, this.getWidth() / 2, this.getHeight() / 2, this);
//
//        try {
////            databoard = "";
//            String[] splitData = databoard.split(CommonFeatures.splitCharactor_5);
////            numbcol = Integer.parseInt(splitData[0]);// số cột
////            numbrow = Integer.parseInt(splitData[1]);// số dòng
////            initBoard(numbcol, numbrow, splitData[2]);// board game
//
//        } catch (Exception ex) {
//            Log.e(TAG, ex.getMessage());
////            this.bmBall2 = Bitmap.createScaledBitmap(bmBallred, (int) (bmBallred.getWidth() * scaleValue), (int) (bmBallred.getHeight() * scaleValue), true);
//            ///for test color
////        this.t_l_Ball_Temp = new Ball(bmBall2, 50, this.getHeight() / 2 + 200, this);
////        this.t_r_Ball_Temp = new Ball(bmBall2, 350, this.getHeight() / 2 + 200, this);
////        this.b_l_Ball_Temp = new Ball(bmBall2, 50, this.getHeight() / 2 + 400, this);
////        this.b_r_Ball_Temp = new Ball(bmBall2, 350, this.getHeight() / 2 + 400, this);
//
////            numbcol = this.getWidth() / (ballWidth);
////            numbrow = this.getHeight() / (ballHeight);
////
////            initBoard(numbcol, numbrow, "");
//        }
        initCircleVogel();
        initTime();
        numberToFind =nextNumber();
        updateNumber(numberToFind);
    }

    private void initCircleVogel()
    {
        mapNumberBall.clear();

        //int[] fibs = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040 };

        int centerx = this.getWidth() / 2;
        int centery = this.getHeight() / 2;
        int j = 0, i = 0, k = 0;

        InitNumberArray();

        for (j = 0, i = j + 1; j < numberLength || k < numberLength ; j++, i = j + 1)
        {
//            int typeC = rand.nextInt(3);
            NumberBall c = RandCircle(i, centerx, centery, numberArray[k], zoomBoard, cR );
            if(c != null) {
                mapNumberBall.put(c.getValue(), c);
                k++;
            }
            if(j > numberLength * 10){
                break;
            }

            //Console.WriteLine(String.Format("({0},{1})", x, y));
        }
    }

    private NumberBall RandCircle(int i, int centerx, int centery, int value, int zoom, int cR)
    {
        //double goldenRatio_phi = (1 + Math.Sqrt(5)) / 2.0;
        double goldenRatio_phi = 0.618033988749895f;
        float s = 0.5f;
        float v = 0.99f;
        float h = 0.99f;
        double r = Math.sqrt(i);
        double theta = i * 2 * Math.PI / (goldenRatio_phi * goldenRatio_phi);

        double x = (Math.cos(theta) * r) * zoom;
        double y = (Math.sin(theta) * r) * zoom;

        int rX = (int)((double)(centerx) + x);
        int rY = (int)((double)(centery) + y);

        if(rX - cR < 0 || rY - cR < 0 || rX + cR > this.getWidth() || rY + cR > this.getHeight()){
            return null;
        }

        NumberBall c = new NumberBall(null, rX - cR, rY - cR, this);
//        c.setHeight(cR * 2);
//        c.setWidth(cR * 2);
        c.setR(cR);
        c.setrX(rX);
        c.setrY(rY);
        c.setValue(value);
        h = (float)rand.nextDouble();
        c.setBallColor(hsv_to_rgb_By_Int(h, s, v));
//        Rectangle ract = new Rectangle();
//        ract.
        return c;
    }

    private int hsv_to_rgb_By_Int(float h, float s, float v)
    {
        float r = 0, g = 0, b = 0;
        try
        {
            int h_i = (int)(h * 6);
            float f = (h * 6 - h_i);
            float p = v * (1 - s);
            float q = v * (1 - f * s);
            float t = v * (1 - (1 - f) * s);

            switch (h_i)
            {
                case 0:
                    r = v;
                    g = t;
                    b = p;
                    break;
                case 1:
                    r = q;
                    g = v;
                    b = p;
                    break;
                case 2:
                    r = p;
                    g = v;
                    b = t;
                    break;
                case 3:
                    r = p;
                    g = q;
                    b = v;
                    break;
                case 4:
                    r = t;
                    g = p;
                    b = v;
                    break;
                case 5:
                    r = v;
                    g = p;
                    b = q;
                    break;
            }
        }
        catch (Exception exx)
        {
            Log.e(TAG, exx.getMessage());
            return Color.BLACK;
        }

        String floatV = (r * 256) + "";
        int red = Integer.parseInt(floatV.contains(".") ? floatV.substring(0, floatV.indexOf(".")) : floatV);
        floatV = (g * 256) + "";
        int green = Integer.parseInt(floatV.contains(".") ? floatV.substring(0, floatV.indexOf(".")) : floatV);
        floatV = (b * 256) + "";
        int blue = Integer.parseInt(floatV.contains(".") ? floatV.substring(0, floatV.indexOf(".")) : floatV);

        return Color.rgb(red, green, blue);
    }

    private void InitNumberArray()
    {
        int j = 0, i = 0, temp;
        for (i = 0; i < this.numberLength; i++)
        {
            numberArray[i] = i + 1;
        }
        for (i = 0; i < this.numberLength; i++)
        {
            j = i + rand.nextInt(this.numberLength-i);
            temp = numberArray[i];
            numberArray[i] = numberArray[j];
            numberArray[j] = temp;
        }
    }

    private void initTime() {
        ProgressBar pgbar = ((Activity) (this.getContext())).findViewById(R.id.progressBar);

        gameTimer = new GameTimer();
        gameTimer.initTime(pgbar);
    }

    private void updateNumber(int numberToView) {
        TextView textView = ((Activity) (this.getContext())).findViewById(R.id.textView);
        if (textView != null) {
            textView.setText(numberToView + "");
        }
    }

    public void onPause() {
        this.gameThread.pause();
    }

    public void onResume() {
        this.gameThread.unpause();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
//        this.tiger = new Tiger(bmTiger, 50, this.getHeight() / 2, this);
//        this.ball = new Ball(bmBallred, 50, this.getHeight() / 2, this);

        initResource();
        initializeGame();

        if (this.gameThread == null || this.gameThread.isAlive()) {
            this.gameThread = new GameThread(this, surfaceHolder);
        }
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        this.gameThread.setRunning(false);
        while (retry) {
            try {
                // Luồng cha, cần phải tạm dừng chờ GameThread kết thúc.
                this.gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                retry = false;
                e.printStackTrace();
            }
//            retry = true;
        }
    }
    @Override
    public void update() {
//        if (tiger != null) {
//            tiger.update();
//        }
//        if (ball != null) {
//            ball.update();
//        }
        if(mapNumberBall != null && mapNumberBall.size() > 0){
            for (Map.Entry<Integer, NumberBall> entry : mapNumberBall.entrySet()) {
                entry.getValue().update();
            }
        }


        if (score != null) {
            score.update();
        }
        if (gameTimer != null) {
            gameTimer.update(0);
        }

    }

    private void endGame() {
        saveData(true);
//            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//
//            dialog.setMessage("This is a demo");
//
//            dialog.setPositiveButton("Buy the full version", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface arg0, int arg1) {
//                    Toast.makeText(getBaseContext(), "BUY IT", Toast.LENGTH_LONG).show();
//                }
//            });
//
//            dialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface arg0, int arg1) {}
//            });
//
//            dialog.show();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        if (ball != null) {
//            ball.draw(canvas);
//        }


        if (score != null) {
            score.draw(canvas);
        }
//        if (tiger != null) {
//            tiger.draw(canvas);
//        }

        if(mapNumberBall != null && mapNumberBall.size() > 0){
            for (Map.Entry<Integer, NumberBall> entry : mapNumberBall.entrySet()) {
                entry.getValue().draw(canvas);
            }
        }

        ///for test color
//        if(t_l_Ball_Temp != null){
//            t_l_Ball_Temp.draw(canvas);
//        }
//        if(t_r_Ball_Temp != null){
//            t_r_Ball_Temp.draw(canvas);
//        }
//        if(b_l_Ball_Temp != null){
//            b_l_Ball_Temp.draw(canvas);
//        }
//        if(b_r_Ball_Temp != null){
//            b_r_Ball_Temp.draw(canvas);
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//
//        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//
//        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//
//        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
//            test();
            selectBall(x, y);
            return true;
        }


        return false;
    }

    private void test() {
//        NumberBall numberBall = mapNumberBall.get(numberToFind);
//        numberBall.choose(true);
//        isNext = true;
//        nextNumber();

    }

    private int nextNumber(){
        int returnNumber = 1;
        if(false){
            returnNumber = numberIndexNext++;
        }else{
            returnNumber = nextRandNumber(numberIndexNext++);
        }
        return returnNumber;
    }

    private int nextRandNumber(int numberIndexNext) {

        int j = numberIndexNext + rand.nextInt(this.numberLength-numberIndexNext);
//        int temp = numberArray[numberIndexNext];
//        numberArray[numberIndexNext] = numberArray[j];
//        numberArray[j] = temp;
        return j;
    }

    private void selectBall(int x, int y) {
        NumberBall numberBall = mapNumberBall.get(numberToFind);
        if(numberBall == null){
            return;
        }else{
            if(numberBall.isTouched(x, y)){
                numberBall.choose(true);
                isNext = true;
                numberToFind = nextNumber();
                updateNumber(numberToFind);
            }
        }
    }

    private void initResource() {
    }

//    private Bitmap createBigImgBall(int randBall) {
//
//        switch (randBall) {
//            case 0:
//                return bmBallRedDefaultSize;
//            case 1:
//                return bmBallBlueDefaultSize;
//            case 2:
//                return bmBallGrayDefaultSize;
//            case 3:
//                return bmBallGreenDefaultSize;
//            case 4:
//                return bmBallMagentaDefaultSize;
//            case 5:
//                return bmBallYellowDefaultSize;
//            default:
//                Log.e(TAG, "Value randBall is too large");
//                return bmBallYellowDefaultSize;
//        }
//    }
}
