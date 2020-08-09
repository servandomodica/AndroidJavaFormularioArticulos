package com.example.pruebasharedpreferences4;

import java.util.StringTokenizer;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class MainActivity extends Activity {
	EditText et1,et2,et3,et4;
	

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);
    }

    public void guardar (View v) {
    	String separador="|";
    	if (et2.getText().toString().indexOf(separador)!=-1) { //busco la negación de -1, es decir, negar que NO lo encontró
    		Toast.makeText(this, "No se permite el caracter |", Toast.LENGTH_LONG).show();
    	}else{
    		String codigo=et1.getText().toString();
	    	String descripcion=et2.getText().toString();
	    	String precio=et3.getText().toString();
	    	String stock=et4.getText().toString();
	    	
	    	SharedPreferences prefe1=getSharedPreferences("articulo", Context.MODE_PRIVATE);
	    	
	    	Editor editor1=prefe1.edit();
	    	editor1.putString(codigo, descripcion+"|"+precio+"|"+stock);
	    	editor1.commit();
	    	
	    	et1.setText("");
	    	et2.setText("");
	    	et3.setText("");
	    	et4.setText("");
	    	Toast.makeText(this, "El artículo fue almacenado", Toast.LENGTH_LONG).show();
	    }
    }
    
    public void recuperar (View v) {
    	String codigo=et1.getText().toString();
    	
    	SharedPreferences prefe1=getSharedPreferences("articulo", Context.MODE_PRIVATE);
    	    	
    	String dato=prefe1.getString(codigo, "");
    	if (dato.length()>0) {
    		StringTokenizer st=new StringTokenizer(dato,"|");
    		String descripcion=st.nextToken();
    		String precio=st.nextToken();
    		String stock=st.nextToken();
    		et2.setText(descripcion);
    		et3.setText(precio);
    		et4.setText(stock);    		
    	}else{
    		Toast.makeText(this, "No existe ese código", Toast.LENGTH_LONG).show();
        	et2.setText("");
        	et3.setText("");
        	et4.setText("");
    	}
    }
    
    public void borrar (View v) {
    	String codigo=et1.getText().toString();
    	
    	SharedPreferences prefe1=getSharedPreferences("articulo", Context.MODE_PRIVATE);
    	
    	String dato=prefe1.getString(codigo, "");
    	if (dato.length()>0) {
	    	Editor editor1=prefe1.edit();
	    	editor1.remove(codigo);
	    	editor1.commit();
	    	Toast.makeText(this, "El código ha sido borrado", Toast.LENGTH_LONG).show();
	    	et1.setText("");
	    	et2.setText("");
	    	et3.setText("");
	    	et4.setText("");
	    }else{
	    	Toast.makeText(this, "No existe ese código", Toast.LENGTH_LONG).show();
        	et2.setText("");
        	et3.setText("");
        	et4.setText("");
	    }
    }
    
}
