# CodeChallenge

- Fetch the data:
  * Posts: http://jsonplaceholder.typicode.com/posts/
  * Users: http://jsonplaceholder.typicode.com/users/
  * Albums: http://jsonplaceholder.typicode.com/albums/
  * Photos: http://jsonplaceholder.typicode.com/photos/
- Persist the data in DataBase with relationships
- Show data in Master/Detail view
  * Master view: post with title and user address. Post can be deleted.
  * Detail view: post with title, body and related albums and photos
- Use lazy loading with cache for images

Based on
* Architecture components
* RxJava
* Glide

TODO:
- Implement paging using `androidx.paging` - The problem now is that number of items fetched from db must be equal to number of items receiced by adapter/`Paging consumer`. Due to flatteing Albums (`Mapper.unfoldList(it, AlbumWithPhotosToAlbumListItemMapper())` into [Album, Photo, Photo, ...] needed for sticky headers adapter, it changes. Flattening list should be considered on db query level or custom `DataSource` class needs to be implemented.
Progress can be tracked in https://github.com/Bresiu/CodeChallenge/tree/wrapped_album  branch
- Show / hide photos of an album (collapsed by default)
- Add search bar in Master view
- Add data binding for Detail view
- Implement `@GlideModule` - currently `RequestOptions` are just shared between calls
- Full resolution photo can be displayed after user clicks on thumbnail
