package is2.helloconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MiObjectInputStream extends ObjectInputStream
{
    public MiObjectInputStream(ObjectInputStream out) throws IOException
    {
        super(out);
    }
    protected MiObjectInputStream() throws IOException, SecurityException
    {
        super();
    }
    public MiObjectInputStream(InputStream inputStream) throws IOException {
		super(inputStream);
	}
	protected void readStreamHeader() throws IOException
    {
    }
}
