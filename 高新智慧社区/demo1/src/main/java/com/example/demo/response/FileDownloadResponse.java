package com.example.demo.response;

public class FileDownloadResponse{
        private String fileName;
        private byte[] fileData ;

        public FileDownloadResponse (String fileName, byte[] fileData) {
            this. fileName = fileName ;
            this. fileData = fileData;
        }

        public String getFileName(){
            return fileName;
        }

        public void setFileName (String fileName) {
            this.fileName = fileName;
        }
        public byte[] getFileData() {
            return fileData;
        }
        public void setFileData(byte[] fileData) {
            this. fileData=fileData;
        }
}
