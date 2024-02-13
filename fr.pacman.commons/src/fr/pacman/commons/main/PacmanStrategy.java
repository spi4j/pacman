package fr.pacman.commons.main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.eclipse.acceleo.engine.AcceleoEngineMessages;
import org.eclipse.acceleo.engine.AcceleoEnginePlugin;
import org.eclipse.acceleo.engine.AcceleoEvaluationException;
import org.eclipse.acceleo.engine.generation.strategy.WorkspaceAwareStrategy;
import org.eclipse.acceleo.engine.generation.writers.AbstractAcceleoWriter;

/**
 * Stratégie de génération de Pacman : Basée sur la {@link WorkspaceAwareStrategy}.
 * @author MINARM
 */
public class PacmanStrategy extends WorkspaceAwareStrategy
{

   private final String _lineDelimiter;

   /**
    * Constructeur de stratégie avec délimiteur de lignes
    * @param p_lineDelimiter
    *           le délimiteur de lignes
    */
   public PacmanStrategy (final String p_lineDelimiter)
   {
      _lineDelimiter = p_lineDelimiter;
   }

   /**
    * {@inheritDoc}
    * 
    * @see org.eclipse.acceleo.engine.generation.strategy.AbstractGenerationStrategy#createWriterFor(java.io.File, org.eclipse.acceleo.engine.generation.writers.AbstractAcceleoWriter, boolean, boolean, java.lang.String)
    */
   @Override
   public AbstractAcceleoWriter createWriterFor (final File p_file, final AbstractAcceleoWriter p_previous,
            final boolean p_appendMode, final boolean p_hasJMergeTags, final String p_charset) throws IOException
   {

      // Copier-coller de WorkspaceAwareStrategy pour instancier un Writer Pacman

      final AbstractAcceleoWriter v_writer;
      final boolean v_fileExisted = p_file.exists();

      if (p_file.isDirectory())
      {
         throw new AcceleoEvaluationException(AcceleoEngineMessages.getString(
                  "AcceleoEvaluationContext.FileNameIsDirectory", p_file)); //$NON-NLS-1$
      }

      if (p_charset != null)
      {
         if (Charset.isSupported(p_charset))
         {
            v_writer = new PacmanWriter(_lineDelimiter, p_file, p_appendMode, p_hasJMergeTags, p_charset);
         }
         else
         {
            final String v_message = AcceleoEngineMessages.getString(
                     "AcceleoGenerationStrategy.UnsupportedCharset", p_charset); //$NON-NLS-1$
            AcceleoEnginePlugin.log(v_message, false);
            v_writer = new PacmanWriter(_lineDelimiter, p_file, p_appendMode, p_hasJMergeTags);
         }
      }
      else
      {
         v_writer = new PacmanWriter(_lineDelimiter, p_file, p_appendMode, p_hasJMergeTags);
      }
      if (p_appendMode && v_fileExisted)
      {
         v_writer.append(LINE_SEPARATOR);
      }
      return v_writer;
   }

}
