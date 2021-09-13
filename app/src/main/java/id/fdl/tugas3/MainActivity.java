package id.fdl.tugas3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everyday);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_every_day, RoomMainFragment.newInstance())
                    .commitNow();
        }
    }

    public void goToRoom(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, RoomFragment.newInstance())
                .commitNow();
    }
}