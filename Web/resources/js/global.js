$(document).ready(function() {

  $(".addSongButton").click(function(){
    $.ajax({
      type: "post",
      url: 'addSongToSession.php',
      data: { song: $("#songInput").val() },
      success: function(data)
      {
        var data = JSON.parse(data);
        console.log(data);

        var row="<tr id='song_row"+data[0]+"'>";
        row+="<td class='mdl-data-table__cell--non-numeric'>";
        row+=$("#songInput").val();
        row+="</td>";
        row+="<td class='delete_row'>";
        row+="<span class='btn btn-danger remove_class' id='"+data[0]+"'>";
        row+="<i class='material-icons'>clear</i>";
        row+="</span>";
        row+="</td>";
        row+="</tr>";

        $(".addedSongs").append(row);

      }
    })

  });

  $("table").on('mousedown', '.remove_class',function(e){
    $.ajax({
      type: "post",
      url: '/ECSE%20321%20Project/Group01/Web/removeSongFromSession.php',
      data: { id: e.currentTarget.id },
      success: function(data)
      {
        var data = JSON.parse(data);
        console.log(data);

        var target="#song_row"+e.currentTarget.id;
        $(target).remove();
      }
    })

  });
});
