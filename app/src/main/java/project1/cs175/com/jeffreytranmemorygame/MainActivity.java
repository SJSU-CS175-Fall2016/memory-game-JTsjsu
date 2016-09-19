package project1.cs175.com.jeffreytranmemorygame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {
        final Intent intent = new Intent(MainActivity.this,
                GameBoard.class);
        new AlertDialog.Builder(MainActivity.this).setTitle("Continue?").setMessage("Continue where you left off?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("startover", true);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intent.putExtra("startover", false);
                        startActivity(intent);
                    }
                })
                .setIcon(R.drawable.pokeball)
                .show();
    }

    public void rules(View view) {
        Intent intent = new Intent(MainActivity.this,
                RulesView.class);
        startActivity(intent);
    }
}
