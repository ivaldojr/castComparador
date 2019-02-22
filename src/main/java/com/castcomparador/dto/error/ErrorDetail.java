package com.castcomparador.dto.error;

/**
 * Respostas genericas para erros.
 * @author ivaldo
 */
public class ErrorDetail {

    private String titulo;
    private int status;
    private String descricao;
    private long timeStamp;

    public String getTitle() {
        return titulo;
    }

    public void setTitle(String title) {
        this.titulo = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return descricao;
    }

    public void setDetail(String detail) {
        this.descricao = detail;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ErrorDetail{" +
                "titulo='" + titulo + '\'' +
                ", status=" + status +
                ", descricao='" + descricao + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
