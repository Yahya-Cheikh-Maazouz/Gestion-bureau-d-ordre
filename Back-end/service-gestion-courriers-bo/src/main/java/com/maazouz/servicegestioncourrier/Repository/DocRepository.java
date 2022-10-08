package com.maazouz.servicegestioncourrier.Repository;
import com.maazouz.servicegestioncourrier.Model.DocumentFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocRepository extends JpaRepository<DocumentFile,Long> {
}
