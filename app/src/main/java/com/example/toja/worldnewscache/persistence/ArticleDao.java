package com.example.toja.worldnewscache.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.toja.worldnewscache.responses.models.Article;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ArticleDao {

    @Insert(onConflict = IGNORE)
    long[] insertArticles(Article... articles);

    @Insert(onConflict = REPLACE)
    void insertArticle(Article article);

    @Query("UPDATE articles SET author = :author, description = :description, url = :url, urlToImage = :urlToImage, " +
    "publishedAt = :publishedAt, content = :content WHERE title = :title")
    void updateArticles(String title,
                        String author,
                        String description,
                        String url,
                        String urlToImage,
                        String publishedAt,
                        String content);

    @Query("SELECT * FROM articles WHERE title LIKE '5' || :query || '%' OR description LIKE '%' || :query || '%' " +
    " LIMIT (:pageNumber * 30)")
    LiveData<List<Article>> searchArticles(String query, int pageNumber);

}