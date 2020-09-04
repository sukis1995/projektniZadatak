var testApp=angular.module("testApp",["ngRoute"]);


testApp.controller("HomeCtrl", function($scope){
	
	
	$scope.message="Dobro dosli na sajt Banki";
	
})


testApp.controller("RacuniCtrl", function($scope,$http,$location){
	
	
	$scope.banke=[];
	$scope.banke.tipoviRacuna=[];
	$scope.racuni=[];
	$scope.tipoviRacuna=[];
	
	
	$scope.racun={};
	$scope.racun.id="";
	$scope.racun.imePrezime="";
	$scope.racun.jmbg="";
	$scope.racun.brojRacuna="";
	$scope.racun.stanjeRacuna="";
	$scope.racun.idTipRacuna="";
	$scope.racun.nazivTipRacuna="";
	$scope.racun.idBanka="";
	$scope.racun.nazivBanka="";
	
	
	$scope.pretraga={};
	$scope.pretraga.idBanka="";
	$scope.pretraga.jmbg="";
	
	$scope.totalPages="";
	$scope.pageNum=0;
	
	
	
	
	var getBanke= function(){
		
		
		$http.get("/api/banke")
		.then(function uspeh(res){
			$scope.banke=res.data;
		}, function error(res){
			alert("Doslo je do greske prilikom ucitavanja banki");
		})
	}
	
	var getRacuni= function(){
		var config= {params:{}};
		if($scope.pretraga.jmbg!=""){
			config.params.jmbg=$scope.pretraga.jmbg;
		}
		if($scope.pretraga.idBanka!=""){
			config.params.idBanka=$scope.pretraga.idBanka;
		}
		config.params.pageNum=$scope.pageNum;
		
		$http.get("/api/racuni", config)
		.then(function uspeh(res){
			$scope.racuni=res.data;
			$scope.totalPages=res.headers("totalPages");
		}, function error(res){
			alert("Doslo je do greske prilikom ucitavanja racuna");
		})
	}
	
	getBanke();
	getRacuni();
	
	
	
	$scope.getTipoviRacuna=function(id){
		
		$http.get("/api/banke/"+$scope.racun.idBanka+"/tipovi-racuna")
		.then(function upseh(res){
			$scope.tipoviRacuna=res.data;
		}, function error(res){
			alert("Doslo je do greske prilikom ")
			
		})
	}
	
	
	$scope.dodaj=function(){
		$http.post("/api/racuni", $scope.racun)
		.then(function uspeh(res){
			getRacuni();
			$scope.racun.id="";
			$scope.racun.imePrezime="";
			$scope.racun.jmbg="";
			$scope.racun.brojRacuna="";
			$scope.racun.idBanka="";
		}, function error(res){
			alert("Doslo je do greske prilikom dodavanja novog racuna");
		})
	}
	
	$scope.goToEdit=function(id){
		$location.path("/racuni/edit-racuni/"+id);
	}
	
	$scope.obrisi= function(id){
		$http.delete("/api/racuni/"+id)
		.then(function uspeh(res){
			$scope.pageNum=0;
			getRacuni();
		}, function error(res){
			alert("Doslo je do greske prilikom brisanja");
		})
	}
	
	$scope.pretrazi=function(){
		getRacuni();
	}
	
	$scope.promeniStranicu=function(direction){
		var number=parseInt(direction);
		$scope.pageNum=parseInt($scope.pageNum) + number;
		getRacuni();
	}
	
	$scope.goToPrenos=function(){
		$location.path("/racuni/prenos")
	}
	
	
	
	
})


testApp.controller("EditCtrl", function($scope,$http,$location,$routeParams){
	
	
	var id = $routeParams.id;

	$scope.racun={};
	$scope.racun.id="";
	$scope.racun.imePrezime="";
	$scope.racun.jmbg="";
	$scope.racun.brojRacuna="";
	$scope.racun.stanjeRacuna="";
	$scope.racun.idTipRacuna="";
	$scope.racun.nazivTipRacuna="";
	$scope.racun.idBanka="";
	$scope.racun.nazivBanka="";
	
	
	var getRacun= function(){
		$http.get("/api/racuni/" + id)
		.then(function uspeh(res){
			$scope.racun=res.data;
		}, function error(res){
			alert("Doslo je do greske");
		})
	}
	
	getRacun();
	
	
	$scope.edit=function(){
		$http.put("/api/racuni/"+id, $scope.racun)
		.then(function uspeh(res){
			$location.path("/racuni");
		}, function error(res){
				alert("Doslo je do greske ");
		})
	}
	
	
})

testApp.controller("PrenosCtrl", function($scope,$location,$http){
	
	$scope.prenos={};
	$scope.prenos.uplatilac="";
	$scope.prenos.primalac="";
	$scope.prenos.iznos="";
	
	
	$scope.prenesi=function(){
		var config= {params:{}};
		config.params.uplatilac=$scope.prenos.uplatilac
		config.params.primalac=$scope.prenos.primalac
		config.params.iznos=$scope.prenos.iznos
		

		$http.get("/api/banke/prenos",config)
		.then(function uspeh(res){
			$location.path("/racuni");
		}, function error(res){
			alert("Doslo je do greske prilikom transfera novca");
		})
	}
	
	
	
	
})
























testApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
		})
		.when('/racuni', {
			templateUrl : '/app/html/racuni.html'
		})
		.when('/racuni/edit-racuni/:id', {
			templateUrl : '/app/html/edit-racuni.html'
		})
		.when('/racuni/prenos', {
			templateUrl : '/app/html/prenos-racuni.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);