angular.module('app').controller('productInfoController', function ($scope, $http, $localStorage, $location, $routeParams) {
    const contextPath = 'http://localhost:8080/api/v1'

    $scope.loadProduct = function (productId) {
        console.log("call")
        $http({
            url: "/api/v1/product/" + productId,
            method: "GET"
        }).then(function (response) {
            $scope.product = response.data;
            console.log($scope.product)
        });
    };

    if ($routeParams.productId) {
        $scope.loadProduct($routeParams.productId)
    }
});