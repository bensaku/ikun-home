import win32com.client
import docx
from pathlib import Path
import sys

if not len(sys.argv) == 4:
    print("Usage: python word_pdf.py wordFilename - pdfFilename")
    sys.exit()

wordFilename = sys.argv[1]
pdfFilename = sys.argv[3]

path = str(Path.cwd())
wdFormatPDF = 17
wordObj = win32com.client.Dispatch('Word.Application')
docObj = wordObj.Documents.Open(path + "//" + wordFilename)

docObj.SaveAs(path + "//" + pdfFilename, FileFormat=wdFormatPDF)
docObj.Close()

wordObj.Quit()