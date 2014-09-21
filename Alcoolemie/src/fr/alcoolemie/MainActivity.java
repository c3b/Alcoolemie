package fr.alcoolemie;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	
	//Property
	private float coefDiffu = (float)0.7;
	
	//radio button selection
	private void ecouteRadio(){
		((RadioGroup)findViewById(R.id.grpRadioSexe)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(((RadioButton)findViewById(R.id.rdHomme)).isChecked()){
					Toast.makeText(MainActivity.this, "Homme", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(MainActivity.this, "femme", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
	
	   // Ecoute sur le bouton Calcul
	     
	    private void ecouteCalcul() {
			((Button)findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					String txtPoids = ((EditText)findViewById(R.id.txtPoids)).getText().toString();
					String txtNbVerre = ((EditText)findViewById(R.id.txtnbVerre)).getText().toString();
					if ((!(txtPoids.equals(""))) && (!(txtNbVerre.equals("")))) {
						calcTxAlcool(Integer.parseInt(txtPoids), Integer.parseInt(txtNbVerre));
					}else{
						Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs !", Toast.LENGTH_SHORT).show();
					}
				}
			});    	
	    }
	    
	    //calcul taux alcoolemie
	    private void calcTxAlcool(int poids, int nbVerres){
	    	int uniteAlcool = 10;
	    	if(poids != 0 && nbVerres != 0){
	    		float tauxAlcool =(nbVerres * uniteAlcool) / (poids * coefDiffu);
	    		//affichahe final du taux
	    	    gestionAffichage(tauxAlcool);
	    }
	    	
}
	    
	    
	    
	    public void gestionAffichage(float taux){
	    	//recuperation des 2 obkets graphiques
	    	TextView lblTxAlcool = (TextView) findViewById(R.id.lblTxAlcool);
	    	ImageView imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
	    	int coef = 0;
	    	if(taux < 0.5){
	    		lblTxAlcool.setTextColor(Color.GREEN);
	    		lblTxAlcool.setText("Bonne route !");
	    		imgSmiley.setImageResource(R.drawable.ok);
	    		
	    	}else if(taux < 0.7){
	    		lblTxAlcool.setTextColor(Color.MAGENTA);
	    		coef = 2;
	    		lblTxAlcool.setText(String.format("%.01f", taux)+"g d'alcool = risque d'accident mortel x" + coef+"!");
	    		imgSmiley.setImageResource(R.drawable.deux);
	    	}else if(taux < 0.8){
	    		lblTxAlcool.setTextColor(Color.MAGENTA);
	    		coef = 5;
	    		lblTxAlcool.setText(String.format("%.01f", taux)+"g d'alcool = risque d'accident mortel x" + coef+"!");
	    		imgSmiley.setImageResource(R.drawable.cinq);
	    	}else if(taux < 1.2){
	    		lblTxAlcool.setTextColor(Color.RED);
	    		coef =10;
	    		lblTxAlcool.setText(String.format("%.01f", taux)+"g d'alcool = risque d'accident mortel x" + coef+"!");
	    		imgSmiley.setImageResource(R.drawable.dix);
	    	}else if(taux < 2){
	    		coef =35;
	    		lblTxAlcool.setTextColor(Color.RED);
	    		lblTxAlcool.setText(String.format("%.01f", taux)+"g d'alcool = risque d'accident mortel x" + coef+"!");
	    		imgSmiley.setImageResource(R.drawable.trentecinq);
	    	}else{
	    		coef = 80;
	    		lblTxAlcool.setTextColor(Color.RED);
	    		lblTxAlcool.setText(String.format("%.01f", taux)+"g d'alcool = risque d'accident mortel x" + coef+"!");
	    		imgSmiley.setImageResource(R.drawable.quatrevingt);
	    	}
	    	
	    
	    		
	    }
	    
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ecouteRadio();
        ecouteCalcul();
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
}
