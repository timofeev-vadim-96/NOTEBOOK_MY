```java
java -Dspring.telegram.client.api-id="29851086" \
-Dspring.telegram.client.api-hash="6a43e6e90c8daf737c9302098eca97e8" \
-Dspring.telegram.client.phone="+79997140760" \
-Dspring.telegram.client.database-encryption-key="MIIBCgKCAQEAyMEdY1aR+sCR3ZSJrtztKTKqigvO/vBfqACJLZtS7QMgCGXJ6XIR yy7mx66W0/sOFa7/1mAZtEoIokDP3ShoqF4fVNb6XeqgQfaUHd8wJpDWHcR2OFwv plUUI1PLTktZ9uW2WE23b+ixNwJjJGwBDJPQEQFBE+vfmH0JP503wr5INS1poWg/ j25sIWeYPHYeOrFp/eXaqhISP6G+q2IeTaWTXpwZj4LzXq5YOpk4bYEQ6mvRq7D1 aHWfYmlEGepfaYR8Q0YqvvhYtMte3ITnuSJs171+GDqpdKcSwHnd6FudwGO4pcCO j4WcDuXc2CTHgH8gFTNhp/Y8/SpDOhvn9QIDAQAB" \
-Dspring.datasource.username="postgres" \
-Dspring.datasource.password="admin" \
-Djava.library.path=./windows_x64 \
-jar ./target/v-it-agregator-1.0.0.jar
```

Debian
```java
java -Dspring.telegram.client.api-id="29851086" \
-Dspring.telegram.client.api-hash="6a43e6e90c8daf737c9302098eca97e8" \
-Dspring.telegram.client.phone="+79997140760" \
-Dspring.telegram.client.database-encryption-key="MIIBCgKCAQEAyMEdY1aR+sCR3ZSJrtztKTKqigvO/vBfqACJLZtS7QMgCGXJ6XIR yy7mx66W0/sOFa7/1mAZtEoIokDP3ShoqF4fVNb6XeqgQfaUHd8wJpDWHcR2OFwv plUUI1PLTktZ9uW2WE23b+ixNwJjJGwBDJPQEQFBE+vfmH0JP503wr5INS1poWg/ j25sIWeYPHYeOrFp/eXaqhISP6G+q2IeTaWTXpwZj4LzXq5YOpk4bYEQ6mvRq7D1 aHWfYmlEGepfaYR8Q0YqvvhYtMte3ITnuSJs171+GDqpdKcSwHnd6FudwGO4pcCO j4WcDuXc2CTHgH8gFTNhp/Y8/SpDOhvn9QIDAQAB" \
-Dspring.datasource.username="postgres" \
-Dspring.datasource.password="admin" \
-Djava.library.path=./ \
-jar ./v-it-agregator-1.0.0.jar
```

old commit: 51743dfd01dff6179e2d8f7095729caa4e2222e9
new commit: 5c77c4692c28eb48a68ef1c1eeb1b1d732d507d3