var app = angular.module('MusicManagerApp');
app.controller('AlbumController', function ($scope, $http) {

    $scope.albums = [];

    $scope.searchType = 'album';
    $scope.start = 1;
    $scope.numberOfRecords = 40;
    $scope.searchTerm = 1;

    $scope.loadMore = function () {
        var BUFFER = 10;
        var WAY_POINT = "waypoint";
        var PAGE_SIZE = 40;

        var objectIdentifier = (this.albums.length - BUFFER);
        var object = document.getElementById(objectIdentifier.toString());
        var objAttr = $(object).attr(WAY_POINT);

        if (objAttr == null || objAttr === undefined) {
            $scope.start++;
            $scope.loadData();
        }


    };

    $scope.loadData = function () {
        if ($scope.isBusy === true) return;

        $scope.isBusy = true;

        $http.get('rest/searchService/list/' + $scope.searchType + "/" + $scope.start + "/" + $scope.numberOfRecords + "/" + $scope.searchTerm).success(function (data, status, headers, config) {
            console.log('Came here again');
            if (data !== null && data.albumJSON !== null && data.albumJSON.length > 0) {
                if ($scope.albums == undefined)
                    $scope.albums = [];

                if (!$scope.isSearch)
                    $scope.albums = $scope.albums.concat(data.albumJSON);
                else
                    $scope.albums = data.detail.response.albumJSON;
            }

            $scope.isBusy = false;
        });
    }
});