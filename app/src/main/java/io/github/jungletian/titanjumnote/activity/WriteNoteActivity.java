package io.github.jungletian.titanjumnote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jungletian.titanjumnote.R;
import io.github.jungletian.titanjumnote.util.Constans;

/**
 * Create by JungleTian on 15-8-26 23:56.
 * Email：tjsummery@gmail.com
 */
public class WriteNoteActivity extends AppCompatActivity {

  @Bind(R.id.write_note_et) public EditText mEdit;

  private int requestCode = -1;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_write_note);
    ButterKnife.bind(this);
    mEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override public void onFocusChange(View v, boolean hasFocus) {
        toggleSoftInput(hasFocus);
      }
    });
    getSupportActionBar().setTitle("创建笔记");

    Intent intent = getIntent();
    if (null != intent) {
      String currentContent = intent.getStringExtra(Constans.CURRENT_CONTENT);
      boolean empty = TextUtils.isEmpty(currentContent);
      requestCode = empty ? requestCode = Constans.WRITE_NOTE_REQUEST_CODE
          : Constans.MODIFY_NOTE_REQUEST_CODE;
      if (!empty) {
        mEdit.setText(currentContent);
        mEdit.setSelection(currentContent.length());
      }
    }
  }

  private void toggleSoftInput(boolean hasFocus) {
    // TODO 软件盘弹出
        /*if (hasFocus) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }*/
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void onBackPressed() {
    setData();
    finish();
  }

  private void setData() {
    String text = mEdit.getText().toString();
    Intent data = new Intent();
    data.putExtra(Constans.WRITE_NOTE_DATA, text);
    setResult(requestCode, data);
  }
}
