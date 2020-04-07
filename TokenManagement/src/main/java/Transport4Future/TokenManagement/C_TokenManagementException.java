////Transport4Future////

package Transport4Future.TokenManagement;

public class C_TokenManagementException extends Exception {
	private static final long serialVersionUID = 1L;
	
	String message;

	public C_TokenManagementException(String message) {

		this.message = message;
	}

	public String getMessage() {

		return this.message;
	}
}
