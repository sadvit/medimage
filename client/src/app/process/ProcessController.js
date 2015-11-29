'use strict';

angular.module('medimage').controller('processController', ['$scope', 'processService', 'imageService', function ($scope, processService, imageService) {

    var index = 0;

    var params = {
        type: 'SQUARE',
        localSquare: [
            28,
            1.0
        ]
    };

    $scope.test = function () {
        if (index < $scope.images.length) {
            processService.binary($scope.images[index], params, function (imageId) {
                    $scope.currentImage = imageId;
            });
            index++;
        }
    };

    $scope.openMenu = function (event) {
        angular.element(event.currentTarget).parent().toggleClass('active');
    };

    this.init = function () {
        imageService.getImages(function (images) {
            $scope.images = images;
            $scope.test();
        });
    };

    this.init();

}]);