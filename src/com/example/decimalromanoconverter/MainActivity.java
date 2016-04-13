package com.example.decimalromanoconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText eddecimal, edromano;
	private Button btconverter, btsair, btlimpar;

	private final String ROMANO[] = new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM",
			"M" };
	private final Integer DECIMAL[] = new Integer[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getComponents();
	}

	private void getComponents() {
		btconverter = (Button) findViewById(R.id.btconverter);
		eddecimal = (EditText) findViewById(R.id.eddecimal);
		btsair = (Button) findViewById(R.id.btsair);
		edromano = (EditText) findViewById(R.id.edromano);
		btlimpar = (Button) findViewById(R.id.btlimpar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void limparClick(View view) {
		eddecimal.setText("");
		edromano.setText("");
	}

	public void sairClick(View view) {
		this.finish();
	}

	public void converterClick(View view) {
		if (!eddecimal.getText().toString().isEmpty()) {
			String romano = decimalToRomano(new Integer(eddecimal.getText().toString()));
			edromano.setText(romano);
		} else {
			if (!edromano.getText().toString().isEmpty()) {
				String decimal = romanoToDecimal(edromano.getText().toString());
				eddecimal.setText(decimal);
			} else {
				Toast.makeText(getApplicationContext(), "Informe o Decimal ou o Romano a converter!", Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	private String decimalToRomano(int d) {
		String resultado = "";
		int resto = d;
		int valor;
		int i = DECIMAL.length - 1;
		while (resto != 0) {
			if (resto >= DECIMAL[i]) {
				valor = resto / DECIMAL[i];
				resto = resto % DECIMAL[i];
				resultado += repeteString(ROMANO[i], valor);
			}
			i--;
		}
		return resultado;
	}

	private String romanoToDecimal(String r) {
		Integer resultado = 0;
		for (int i = 0; i < r.length(); i++) {
			for (int rl = ROMANO.length - 1; rl >= 0; rl--) {
				if (!(i + 2 > r.length())) {
					if (ROMANO[rl].length() == 2 && ROMANO[rl].equalsIgnoreCase(r.substring(i, i + 2))) {
						resultado += DECIMAL[rl];
						++i;
						break;
					}
				}
				if (!(i + 1 > r.length())) {
					if (ROMANO[rl].equalsIgnoreCase(r.substring(i, i + 1))) {
						resultado += DECIMAL[rl];
						break;
					}
				}
			}
		}
		return resultado.toString();
	}

	private String repeteString(String str, int qtd) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < qtd; i++) {
			bf.append(str);
		}
		return bf.toString();
	}
}
