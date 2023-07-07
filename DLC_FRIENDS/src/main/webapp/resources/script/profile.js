var file = document.querySelector("#imgFile");
// var result;

// 비동기 데이터 읽기
var reader = new FileReader();

file.addEventListener("change", function(e) {
  console.log("file info:");
  console.log(e.target.files);

  if (this.files && this.files[0]) {
    reader.onload = function() {
      var imageData = reader.result;
      console.log("encoded result:");
      console.log(imageData);
    };
    
    reader.onerror = function(error) {
      console.log("Error!");
    };

    reader.readAsDataURL(e.target.files[0]);
  }

});
