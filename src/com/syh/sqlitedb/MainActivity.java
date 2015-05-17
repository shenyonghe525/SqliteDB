package com.syh.sqlitedb;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
    private PersionDao2 persionDao;
    private Button add,delete,update,query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button)findViewById(R.id.btn_add);
        delete =(Button)findViewById(R.id.btn_delete);
        update = (Button)findViewById(R.id.btn_update);
        query = (Button)findViewById(R.id.btn_query);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        query.setOnClickListener(this);
        persionDao = new PersionDao2(this);
    }

	public void onClick(View v) {
        switch (v.getId()) {
		case R.id.btn_add:
			System.out.println("add");
			Random random = new Random();
			persionDao.add("shenwu", random.nextInt(1000)+"");
			persionDao.add("lisi", random.nextInt(2000)+"");
			persionDao.add("zhangsan", random.nextInt(3000)+"");
			break;
		case R.id.btn_delete:
			System.out.println("delete");
			persionDao.delete("shen");
			break;
		case R.id.btn_query:
			System.out.println("query");
			List<Persion> list = persionDao.getAll();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).toString());
			}
			break;
		case R.id.btn_update:
			System.out.println("update");
			persionDao.update("shen", "134");
			break;
		}		
	}
}
