/**
 * Created by nietaki on 12.06.14.
 */
(function(){

  console.log('bng.js')
  var app = angular.module('bng', [])

  app.controller('BandNameController', function($scope, $http) {
    $scope.formData = {}
    $scope.formData.recipes = [
      {
        name: 'GreenDay',
        example: 'Green Day',
        selected: true
      },
      {
        name: 'TheWhiteStripes',
        example: 'The White Stripes',
        selected: false
      }
    ]
    $scope.uncommonProbability = 0.1
    $scope.nameCount = 20
    console.log('bandnamecontroller')

    $scope.bandNames=["foo"]

    $scope.processForm = function() {
      console.log('processForm')
      var addr = '/bandNames/' +  $scope.uncommonProbability + '/' + $scope.nameCount
      $http.post(addr, $scope.formData)
        .success(function(data){
          console.log('success')
          console.log(data)
          $scope.bandNames = data
        })
        .error(function(data){console.log('error'); console.log(data)})
    }
  });

})();


