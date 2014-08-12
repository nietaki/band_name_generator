/**
 * Created by nietaki on 12.06.14.
 */
(function(){

  console.log('bng.js')
  var app = angular.module('bng', [])

  app.controller('BandNameController', function($scope) {
    console.log('bandnamecontroller')
      $scope.processForm = function() {
        console.log('processForm')
      }
  });

})();


