package spb.academy.android.ex_3_app;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        mediaPlayer = MediaPlayer.create(this, R.raw.cat_meows_sound);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager;

        Configuration config = getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new LinearLayoutManager(this);
        } else {
            //layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        }

        recyclerView.setLayoutManager(layoutManager);

        GaleryAdapter.OnItemClickListener onItemClickListener = new GaleryAdapter.OnItemClickListener() {
            @Override
            public void onClick(Cat cat, int position) {
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
                Toast.makeText(MainActivity.this, String.valueOf(position) + " : " + cat.getUrl() , Toast.LENGTH_SHORT).show();
            }
        };

        RecyclerView.Adapter adapter = new GaleryAdapter(generateCatsGaleryUrl(), onItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private List<Cat> generateCatsGaleryUrl() {
        List<Cat> catList = new ArrayList<>();

        catList.add(new Cat("https://mtdata.ru/u28/photo96CE/20466331848-0/original.jpg"));
        catList.add(new Cat("http://bipbap.ru/wp-content/uploads/2017/08/5114e7b13c84a77355cbec162ca7ff45.jpg"));
        catList.add(new Cat("http://bipbap.ru/wp-content/uploads/2017/09/2189909404.jpg"));
        catList.add(new Cat("http://kakzachem.ru/wp-content/uploads/2018/01/Bez-imeni-2-8.jpg"));
        catList.add(new Cat("https://static2.shop033.com/resources/18/160536/picture/5D/85442141.jpg"));
        catList.add(new Cat("https://gloss.ua/file/t/17/11/28/bqeye_640x360.jpg"));
        catList.add(new Cat("https://obovsem.pp.ua/wp-content/uploads/kotiki-31.jpg"));
        catList.add(new Cat("https://www.telegraph.co.uk/content/dam/pets/2017/01/06/1-JS117202740-yana-two-face-cat-news_trans_NvBQzQNjv4BqJNqHJA5DVIMqgv_1zKR2kxRY9bnFVTp4QZlQjJfe6H0.jpg?imwidth=450"));
        catList.add(new Cat("https://www.kedisahiplendirme.com/wp-content/uploads/2018/04/Scottish-Fold.jpg"));
        catList.add(new Cat("http://honesttopaws.com/wp-content/uploads/sites/5/2017/05/banana-cat-1.png"));
        catList.add(new Cat("http://www.catster.com/wp-content/uploads/2017/10/A-kitten-meowing-with-his-mouth-open.jpg"));
        catList.add(new Cat("https://static.euronews.com/articles/432451/603x339_432451.jpg"));

        return catList;
    }
}
