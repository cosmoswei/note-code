package com.wei.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName books
 */
public class Books implements Serializable {
    /**
     * 
     */
    private Integer bookID;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String author;

    /**
     * 
     */
    private String ISBN;

    /**
     * 
     */
    private Date publicationDate;

    /**
     * 
     */
    private String publisher;

    /**
     * 
     */
    private BigDecimal price;

    /**
     * 
     */
    private Integer stockQuantity;

    /**
     * 
     */
    private String genre;

    /**
     * 
     */
    private String coverImage;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;

    /**
     * 
     */
    private Date eventTime;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getBookID() {
        return bookID;
    }

    /**
     * 
     */
    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    /**
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * 
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * 
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * 
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * 
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 
     */
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * 
     */
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * 
     */
    public String getGenre() {
        return genre;
    }

    /**
     * 
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * 
     */
    public String getCoverImage() {
        return coverImage;
    }

    /**
     * 
     */
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    /**
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     */
    public Date getEventTime() {
        return eventTime;
    }

    /**
     * 
     */
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Books other = (Books) that;
        return (this.getBookID() == null ? other.getBookID() == null : this.getBookID().equals(other.getBookID()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getISBN() == null ? other.getISBN() == null : this.getISBN().equals(other.getISBN()))
            && (this.getPublicationDate() == null ? other.getPublicationDate() == null : this.getPublicationDate().equals(other.getPublicationDate()))
            && (this.getPublisher() == null ? other.getPublisher() == null : this.getPublisher().equals(other.getPublisher()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getStockQuantity() == null ? other.getStockQuantity() == null : this.getStockQuantity().equals(other.getStockQuantity()))
            && (this.getGenre() == null ? other.getGenre() == null : this.getGenre().equals(other.getGenre()))
            && (this.getCoverImage() == null ? other.getCoverImage() == null : this.getCoverImage().equals(other.getCoverImage()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getEventTime() == null ? other.getEventTime() == null : this.getEventTime().equals(other.getEventTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBookID() == null) ? 0 : getBookID().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getISBN() == null) ? 0 : getISBN().hashCode());
        result = prime * result + ((getPublicationDate() == null) ? 0 : getPublicationDate().hashCode());
        result = prime * result + ((getPublisher() == null) ? 0 : getPublisher().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getStockQuantity() == null) ? 0 : getStockQuantity().hashCode());
        result = prime * result + ((getGenre() == null) ? 0 : getGenre().hashCode());
        result = prime * result + ((getCoverImage() == null) ? 0 : getCoverImage().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getEventTime() == null) ? 0 : getEventTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookID=").append(bookID);
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", ISBN=").append(ISBN);
        sb.append(", publicationDate=").append(publicationDate);
        sb.append(", publisher=").append(publisher);
        sb.append(", price=").append(price);
        sb.append(", stockQuantity=").append(stockQuantity);
        sb.append(", genre=").append(genre);
        sb.append(", coverImage=").append(coverImage);
        sb.append(", description=").append(description);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", eventTime=").append(eventTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}