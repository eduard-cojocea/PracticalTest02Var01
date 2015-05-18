package pdsd.systems.cs.pub.ro.practicaltest02var01.graphicuserinterface;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import pdsd.systems.cs.pub.ro.practicaltest02var01.R;
import pdsd.systems.cs.pub.ro.practicaltest02var01.general.Constants;
import pdsd.systems.cs.pub.ro.practicaltest02var01.networkingthreads.ClientThread;
import pdsd.systems.cs.pub.ro.practicaltest02var01.networkingthreads.ServerThread;


public class PracticalTest02Var01MainActivity extends Activity {

    // Server widgets
    private EditText serverPortEditText       = null;
    private Button connectButton            = null;

    // Client widgets
    private Button temperatureButton;
    private Button humidityButton;
    private Button allButton;
    private TextView weatherForecastTextView  = null;

    private ServerThread serverThread             = null;
    private ClientThread clientThread             = null;

    private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
    private class ConnectButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = serverPortEditText.getText().toString();
            if (serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(
                        getApplicationContext(),
                        "Server port should be filled!",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            serverThread = new ServerThread(Integer.parseInt(serverPort));
            if (serverThread.getServerSocket() != null) {
                serverThread.start();
            } else {
                Log.e(Constants.TAG, "[MAIN ACTIVITY] Could not creat server thread!");
            }

        }
    }

    private TemperatureButtonClickListener temperatureButtonClickListener = new TemperatureButtonClickListener();
    private class TemperatureButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            if (serverThread == null || !serverThread.isAlive()) {
                Log.e(Constants.TAG, "[MAIN ACTIVITY] There is no server to connect to!");
                return;
            }

            weatherForecastTextView.setText(Constants.EMPTY_STRING);

            clientThread = new ClientThread(
                    Constants.CLIENT_ADDRESS,
                    Integer.parseInt(Constants.CLIENT_PORT),
                    Constants.CITY,
                    Constants.TEMPERATURE,
                    weatherForecastTextView);
            clientThread.start();
        }
    }

    private HumidityButtonClickListener humidityButtonClickListener = new HumidityButtonClickListener();
    private class HumidityButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            if (serverThread == null || !serverThread.isAlive()) {
                Log.e(Constants.TAG, "[MAIN ACTIVITY] There is no server to connect to!");
                return;
            }

            weatherForecastTextView.setText(Constants.EMPTY_STRING);

            clientThread = new ClientThread(
                    Constants.CLIENT_ADDRESS,
                    Integer.parseInt(Constants.CLIENT_PORT),
                    Constants.CITY,
                    Constants.HUMIDITY,
                    weatherForecastTextView);
            clientThread.start();
        }
    }

    private AllButtonClickListener allButtonClickListener = new AllButtonClickListener();
    private class AllButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            if (serverThread == null || !serverThread.isAlive()) {
                Log.e(Constants.TAG, "[MAIN ACTIVITY] There is no server to connect to!");
                return;
            }

            weatherForecastTextView.setText(Constants.EMPTY_STRING);

            clientThread = new ClientThread(
                    Constants.CLIENT_ADDRESS,
                    Integer.parseInt(Constants.CLIENT_PORT),
                    Constants.CITY,
                    Constants.ALL,
                    weatherForecastTextView);
            clientThread.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_var01_main);

        serverPortEditText = (EditText)findViewById(R.id.server_port_edit_text);
        connectButton = (Button)findViewById(R.id.connect_button);
        connectButton.setOnClickListener(connectButtonClickListener);


        temperatureButton = (Button)findViewById(R.id.temperature_button);
        temperatureButton.setOnClickListener(temperatureButtonClickListener);

        connectButton = (Button)findViewById(R.id.connect_button);
        connectButton.setOnClickListener(connectButtonClickListener);

        connectButton = (Button)findViewById(R.id.connect_button);
        connectButton.setOnClickListener(connectButtonClickListener);

        weatherForecastTextView = (TextView)findViewById(R.id.weather_forecast_text_view);
    }

    @Override
    protected void onDestroy() {
        if (serverThread != null) {
            serverThread.stopThread();
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simulare02_main, menu);
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
