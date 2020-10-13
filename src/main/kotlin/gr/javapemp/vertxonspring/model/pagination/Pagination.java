package gr.javapemp.vertxonspring.model.pagination;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataObject(generateConverter = true)
public class Pagination {
    private int page;
    private int size;

    public Pagination() {
    }

    public Pagination(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public Pagination(JsonObject jsonObject) {
        PaginationConverter.fromJson(jsonObject, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        PaginationConverter.toJson(this, json);
        return json;
    }

    public Pageable toPageable() {
        return PageRequest.of(this.page, this.size);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
