package com.sdc.android.expensify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.Button;

import com.sdc.android.milestone.R;
import com.sdc.android.milestone.R.id;

public class MainActivity extends FragmentActivity {

	public static final int FRAGMENT_DASHBOARD = 0;
	public static final int FRAGMENT_SOURCE = 1;
	public static final int FRAGMENT_VISITOR = 2;
	public static final int FRAGMENT_EXPENSES = 3;
	public static final int FRAGMENT_TRANSACTION = 4;
	public static final int FRAGMENT_REPORTS = 5;
	public static final int FRAGMENT_BACKUP = 6;
	Button b1;

	private SlidingPaneLayout spl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		setNewPage(new DashboardActivity(), FRAGMENT_DASHBOARD);
	}

	private void init() {
		spl = (SlidingPaneLayout) findViewById(id.sliding_pane_layout);
	}

	public void setNewPage(Fragment fragment, int pageIndex) {
		if (spl.isOpen()) {
			spl.closePane();
		}
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.layout_frame, fragment, "currentFragment")
				.commit();

	}

	public void onDashBoard(View v) {
		setNewPage(new DashboardActivity(), FRAGMENT_DASHBOARD);
	}

	public void onAccount(View v) {
		setNewPage(new AccountActivity(), FRAGMENT_SOURCE);
		/*b1=(Button) findViewById(R.id.btnreceipt);
		b1.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.androidhive.loginandregister.RECEIPTACTIVITY"));
				
			}
	});*/
	}

	public void onIncome(View v) {
		setNewPage(new IncomeActivity(), FRAGMENT_VISITOR);
	}
	public void onExpenses(View v) {
		setNewPage(new ExpensesActivity(), FRAGMENT_EXPENSES);
	}
	
	public void onTransaction(View v) {
		setNewPage(new TransactionActivity(), FRAGMENT_TRANSACTION);
	}
	
	public void onReports(View v) {
		setNewPage(new ReportsActivity(), FRAGMENT_REPORTS);
	}
	
	public void onBackup(View v) {
		setNewPage(new BackupActivity(), FRAGMENT_BACKUP);
	}
	

	public void onSliding(View v) {
		if (spl.isOpen()) {
			spl.closePane();
		} else {
			spl.openPane();
		}
	}

}
