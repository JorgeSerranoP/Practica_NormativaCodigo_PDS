////Transport4Future////

package Transport4Future.TokenManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.json.Json;
import javax.json.JsonObject;


public class C_TokenManager {

	public C_TokenRequest readTokenRequestFromJSON(String path) throws C_TokenManagementException {
		C_TokenRequest req = null;

		String fileContents = "";

		final BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			throw new C_TokenManagementException("Error: input file not found.");
		}
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				fileContents += line;
			}
		} catch (IOException e) {
			throw new C_TokenManagementException("Error: input file could not be accessed.");
		}
		try {
			reader.close();
		} catch (IOException e) {
			throw new C_TokenManagementException("Error: input file could not be closed.");
		}

		final JsonObject jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();

		final DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);

		try {
			final String deviceName = jsonLicense.getString("Device Name");
			final Date requestDate = df.parse(jsonLicense.getString("Request Date"));
			final String serialNumber = jsonLicense.getString("Serial Number");
			final String macAddress = jsonLicense.getString("MAC Address");
			
			req = new C_TokenRequest(deviceName, requestDate, serialNumber, macAddress);
		} catch (ParseException pe) {
			throw new C_TokenManagementException("Error: invalid input data in JSON structure.");
		}

		return req;
	}
}
