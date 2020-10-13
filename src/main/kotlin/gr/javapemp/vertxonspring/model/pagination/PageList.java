package gr.javapemp.vertxonspring.model.pagination;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@DataObject(generateConverter = true)
public class PageList {

    private int totalPages;
    private long totalElements;
    private int number;
    private int size;
    private int numberOfElements;
    private JsonArray content;
    private boolean hasContent;
    private boolean isFirst;
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    public PageList(Page data) {
        this.totalPages = data.getTotalPages();
        this.totalElements = data.getTotalElements();
        this.number = data.getNumber();
        this.size = data.getSize();
        this.numberOfElements = data.getNumberOfElements();
        this.content = new JsonArray(
                (List<JsonObject>) data.toList().stream().map(JsonObject::mapFrom).collect(Collectors.toList())
        );
        this.hasContent = data.hasContent();
        this.isFirst = data.isFirst();
        this.isLast = data.isLast();
        this.hasNext = data.hasNext();
        this.hasPrevious = data.hasPrevious();
    }

    public PageList(JsonObject jsonObject) {
        PageListConverter.fromJson(jsonObject, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        PageListConverter.toJson(this, json);
        return json;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public JsonArray getContent() {
        return content;
    }

    public void setContent(JsonArray content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return hasContent;
    }

    public void setHasContent(boolean hasContent) {
        this.hasContent = hasContent;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}
