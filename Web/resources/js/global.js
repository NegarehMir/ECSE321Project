$(document).ready(function() {

  $(".addSongButton").click(function(){
    $.ajax({
      type: "post",
      url: 'addSongToSession.php',
      data: {
        song: $("#songInput").val(),
        duration: $("#durationInput").val()
      },
      success: function(data)
      {
        var data = JSON.parse(data);
        console.log(data);

        var row="<tr id='song_row"+data[0]+"'>";
        row+="<td>";
        row+=$("#songInput").val();
        row+="</td>";
        row+="<td>";
        row+=$("#durationInput").val();
        row+="</td>";
        row+="<td class='delete_row'>";
        row+="<span class='alert button remove_class' id='"+data[0]+"'>";
        row+="X";
        row+="</span>";
        row+="</td>";
        row+="</tr>";

        $("#songInput").val("");
        $("#durationInput").val("")
        $(".addedSongs").append(row);

      }
    })

  });

  $("table").on('mousedown', '.remove_class',function(e){
    $.ajax({
      type: "post",
      url: 'removeSongFromSession.php',
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


  $(".selectSongs").click(function(){

    $(".songs-table tr").each(function(){
      if($(this).hasClass(''))
      {

      }
    });

  });

  $(".select").change(function(){
    if(this.checked)
    {
      $(this).parent().parent().addClass("is-selected");
    }
    else
    {
      $(this).parent().parent().removeClass("is-selected");
    }
  });

});
