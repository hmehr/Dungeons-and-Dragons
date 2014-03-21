
package Model;
/**
 * Single Tone pattern to output messages to the console.
 * It uses a flag to turn off/on the output.
 * @author Hamid
 */
public class OutputConsole
{

    private static OutputConsole _outputConsole = null;
    private final static Object _synObject = new Object();
    private boolean _messageFlag = true;

    /**
     * Default Constructor.
     */
    private OutputConsole()
    {
    }

    /**
     * Static method to write to the console. Uses singleton pattern.
     * @return The only instance of OutputConsole
     */
    public static OutputConsole GetOutputConsoleInstance()
    {
        synchronized (_synObject)
        {
            if (_outputConsole == null)
            {
                _outputConsole = new OutputConsole();
            }
        }
        return _outputConsole;
    }

    /**
     * Sets the flag.
     * @param pMessageFlag The flag to be set. True or false.
     */
    public void setMessageFlag(boolean pMessageFlag)
    {
        this._messageFlag = pMessageFlag;
    }

    /**
     * Writes some message to the output console.
     * @param pMessage The message to write to the output console as info.
     */
    public void Write(String pMessage)
    {
        if (_messageFlag)
            System.out.println("Just for info: " + pMessage);
    }

    /**
     * Writes some message to the output console.
     * @param pMessage The message to write to the output console when exceptions occur.
     */
    public void WriteException(String pMessage)
    {
        if (_messageFlag)
            System.out.println("Exception!!!: " + pMessage);
    }

    /**
     * Writes some message to the output console.
     * @param pMessage The message to write to the output console as warming.
     */
    public void WriteWarning(String pMessage)
    {
        if (_messageFlag)
            System.out.println("Warning!!:" + pMessage);
    }
}
