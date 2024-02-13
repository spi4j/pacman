package fr.pacman.commons.main;

import java.io.File;
import java.io.IOException;

import org.eclipse.acceleo.engine.generation.writers.AcceleoWorkspaceFileWriter;

/**
 * Writer Pacman pour remplacer les retours chariots selon la propriété définie dans le fichier properties à côté du modèle.
 * @author MINARM
 */
public class PacmanWriter extends AcceleoWorkspaceFileWriter
{

   private final String _lineDelimiter;

   /**
    * Constructs a writer that will use JMerge to merge the content of the file existing at path <em>filePath</em> with its new content.
    * 
    * @param p_target
    *           File in which this writer will append text.
    * @param p_appendMode
    *           Tells us whether the former content of the file should be deleted.
    * @param p_merge
    *           Tells us whether we should merge the new generated content with the existing file content.
    * @param p_charset
    *           Encoding that's to be used to create the file with the merged content.
    * @param p_lineDelimiter
    *           le délimiteur de lignes
    * @throws IOException
    *            Thrown if the target cannot be read.
    */
   public PacmanWriter (final String p_lineDelimiter, final File p_target, final boolean p_appendMode,
            final boolean p_merge, final String p_charset) throws IOException
   {
      super(p_target, p_appendMode, p_merge, p_charset);
      _lineDelimiter = p_lineDelimiter;
   }

   /**
    * Constructs a writer that will use JMerge to merge the content of the file existing at path <em>filePath</em> with its new content. Note that the file will be written with the default System encoding if using this.
    * 
    * @param p_target
    *           File in which this writer will append text.
    * @param p_appendMode
    *           Tells us whether the former content of the file should be deleted.
    * @param p_merge
    *           Tells us whether we should merge the new generated content with the existing file content.
    * @param p_lineDelimiter
    *           le délimiteur de lignes
    * @throws IOException
    *            Thrown if the target cannot be read.
    */
   public PacmanWriter (final String p_lineDelimiter, final File p_target, final boolean p_appendMode,
            final boolean p_merge) throws IOException
   {
      super(p_target, p_appendMode, p_merge);
      _lineDelimiter = p_lineDelimiter;
   }

   @Override
   public void write (final String p_str, final int p_off, final int p_len) throws IOException
   {
      final String v_finalStr;
      final int v_finalOff;
      final int v_finalLen;
      if ("\r\n".equalsIgnoreCase(_lineDelimiter))
      {
         v_finalStr = p_str;
         v_finalOff = p_off;
         v_finalLen = p_len;
      }
      else
      {
         // Si le délimiteur de ligne a été changé dans les propriétés, il faut changer le code généré.
         v_finalStr = p_str.substring(p_off, p_len).replace("\r\n", _lineDelimiter);
         v_finalOff = 0;
         v_finalLen = v_finalStr.length();
      }
      super.write(v_finalStr, v_finalOff, v_finalLen);
   }

}
