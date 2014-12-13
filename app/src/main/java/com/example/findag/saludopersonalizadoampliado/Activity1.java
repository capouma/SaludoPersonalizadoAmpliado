package com.example.findag.saludopersonalizadoampliado;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class Activity1 extends ActionBarActivity
{

    // Definimos los complementos necesarios para nuestro spinner.
    TextView lblopcion;
    Spinner spOpciones;
    String[] datos;
    ArrayAdapter<String> adaptador;

        // Comienza el onCreate
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_activity1);// Cargamos el layout

            // Definimos e instanciamos el boton y el RadioGroup
            final Button btnBoton = (Button) findViewById(R.id.b_saludo);// Es necesario castearlo para poder acceder a todos los metodos de button
            final RadioGroup btnRadio = (RadioGroup) findViewById(R.id.RadioGroup01);

            // Este Array contiene las dos opciones posibles de nuestro Spinner.
            datos=new String[] {"Hola","Adios"};

            // Creamos un nuevo adaptador que usaremos para mostrar nuestro Array datos
            adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);

            // Recogemos el string de saludo
            lblopcion=(TextView) findViewById(R.id.saludo);

            // Instanciamos nuestro spinner por su id
            spOpciones=(Spinner) findViewById(R.id.spinner);

            // Preparamos nuestro adaptador.
            adaptador.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            spOpciones.setAdapter(adaptador);

            // Definimos un setOnItemSelectedListener con sus metodos y los sobreescribimos. Haciendo que muestre la opcion seleccionada.
            spOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView parent, View v,int position, long id)
                {
                    lblopcion.setText("Has seleccionado: "+datos[position]);
                }
                @Override
                public void onNothingSelected(AdapterView arg0)
                {
                    lblopcion.setText("ningún dato seleccionado");
                }
            });

            // Definimos el listener onClick
            btnBoton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    // Recogemos la string de saludoSr y saludoSra.
                    String sr = getResources().getString(R.string.saludoSr);
                    String sra = getResources().getString(R.string.saludoSra);


                    TextView textoEntrada = (TextView) findViewById(R.id.entrada);

                    // MUestra un aviso temporal
                    if (textoEntrada.getText().toString().equals(""))
                    {
                        showToast();
                        return;
                    }

                    else
                    {
                        TextView textoSalida = (TextView) findViewById(R.id.saludo);

                        // Definimos una variable String que dependiendo del RadioButton marcado contendra sr o sra.
                        String srSra;
                        if (btnRadio.getCheckedRadioButtonId() == R.id.rdsr)
                        {
                            srSra = sr;

                        }
                        else
                        {
                            srSra= sra;

                        }

                        textoSalida.setText(" " + srSra + " " + textoEntrada.getText());

                        // Obtenemos Fecha y Hora
                        CheckBox timeCheckBox = (CheckBox) findViewById(R.id.checkBox);

                        if (timeCheckBox.isChecked())
                        {
                            DatePicker fecha = (DatePicker) findViewById(R.id.datePicker);
                            String muestraFecha = fecha.getDayOfMonth() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getYear();
                            TimePicker hora = (TimePicker) findViewById(R.id.timePicker);
                            muestraFecha += " " + hora.getCurrentHour() + ":" + hora.getCurrentMinute();
                            textoSalida.setText(" " + srSra + " " + textoEntrada.getText() + " Hoy es " + muestraFecha);
                        }
                    }

                }
            });

            // Mostrar Fechas

            // Instanciamos el checkBox
            CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);

            //Definimos listener OnCheckedChange
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    int visibilidad = isChecked ? View.VISIBLE : View.GONE;
                    View ver = findViewById(R.id.timePicker);
                    ver.setVisibility(visibilidad);
                    ver = findViewById(R.id.datePicker);
                    ver.setVisibility(visibilidad);


                }
            });

        }

      @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_activity1, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings)
            {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        public void showToast()
        {
            Context context = getApplicationContext();
            int duracion = Toast.LENGTH_SHORT;
            CharSequence texto = getResources().getString(R.string.noNombre);
            Toast toast = Toast.makeText(context, texto, duracion);
            toast.show();

        }
    }
