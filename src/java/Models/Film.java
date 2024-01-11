/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

public class Film {

    private int id;
    private String title;
    private String category;
    private int length;
    private String description;
    private int ageLimit;
    private String director;
    private String actors;
    private String warningText;
    private Date publishTime;
    private String origin;
    private String imageUrl;
    private String image_backgroundUrl;
    private int status;

    public Film() {
    }

    public Film(int id, String title, String category, int length, String description, int ageLimit, String director, String actors, String warningText, Date publishTime, String origin, String imageUrl, String image_backgroundUrl, int status) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.length = length;
        this.description = description;
        this.ageLimit = ageLimit;
        this.director = director;
        this.actors = actors;
        this.warningText = warningText;
        this.publishTime = publishTime;
        this.origin = origin;
        this.imageUrl = imageUrl;
        this.image_backgroundUrl = image_backgroundUrl;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getWarningText() {
        return warningText;
    }

    public void setWarningText(String warningText) {
        this.warningText = warningText;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage_backgroundUrl() {
        return image_backgroundUrl;
    }

    public void setImage_backgroundUrl(String image_backgroundUrl) {
        this.image_backgroundUrl = image_backgroundUrl;
    }

}
