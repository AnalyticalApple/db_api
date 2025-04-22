package xyz.matthewsavage.db_api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoApps extends JpaRepository<ObjectApps, Integer> {
}
