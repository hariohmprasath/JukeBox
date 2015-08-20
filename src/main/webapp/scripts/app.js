(function (document) {
    'use strict';

    var app = document.querySelector('#app');

    app.isSearch = false;

    // Close drawer after menu item is selected if drawerPanel is narrow
    app.handleResponse = function (request) {
        console.log(request.detail.response);
    };


    app.addEventListener('dom-change', function () {
        app.generateAlbumRequest('');
        app.toggle();

        document.querySelector("#albumSearch").addEventListener('keyup', function (e) {
            if (e.target.value !== undefined && (e.target.value.length > 3 || e.target.value.length == 0)) {
                app.isSearch = true;
                app.generateAlbumRequest(e.target.value);
            }
        });
    });

    app.generateAlbumRequest = function (searchTerm) {
        var solrRequest = document.querySelector("#solrRequest");
        var ironPages = document.querySelector("iron-pages");
        var searchRequest = {};
        if (ironPages.selected == 0)
            searchRequest.searchType = 'album';
        else
            searchRequest.searchType = 'song';

        searchRequest.start = 0;
        searchRequest.searchTerm = searchTerm;
        searchRequest.numberOfRecords = 80;

        solrRequest.params = searchRequest;
        solrRequest.generateRequest();
    };

    app.handleResponse = function (data) {
        var boxList = document.querySelector("#boxList");
        var songList = document.querySelector("#songList");

        if (data !== null && data.detail !== null && data.detail.response !== null
            && data.detail.response.albumJSON !== null) {
            if (boxList !== null && boxList !== undefined) {

                if (boxList.albums == undefined || app.isSearch)
                    boxList.albums = [];

                if (songList.albums == undefined || app.isSearch)
                    songList.albums = [];

                var items = data.detail.response.albumJSON;
                var albums = [];
                for (var i = 0; i < items.length; i++) {
                    var x = items[i];
                    var targetAlbum = JSON.parse(JSON.stringify(x));
                    var songNames = [];
                    var songUrls = [];
                    if (!(x.songNameList instanceof Array)) {
                        songNames[0] = x.songNameList;
                        songUrls[0] = x.songUrlList;
                    } else {
                        songNames = x.songNameList;
                        songUrls = x.songUrlList;
                    }

                    targetAlbum.songNameList = songNames;
                    targetAlbum.songUrlList = songUrls;
                    albums.push(targetAlbum);
                }


                boxList.albums = boxList.albums.concat(albums);
                songList.albums = songList.albums.concat(albums);

                // Mark to say loading is complete
                boxList.isLoading = false;
                songList.isLoading = false;

            }
        } else
            boxList.albums = [];

        app.isSearch = false;
    };

    app.handleError = function (error) {
        console.log('Error has happened ' + data);
    };

    app.toggle = function () {
        var sideNav = document.querySelector("#sideNav");
        var drawerMenu = document.querySelector("#toggleDrawerMenu");
        var mainMenu = document.querySelector("#toggleMainMenu");

        if (!sideNav.narrow) {
            mainMenu.toggleClass("hide-element");
            drawerMenu.toggleClass("show-element");
        } else {
            drawerMenu.toggleClass("show-element");
            mainMenu.toggleClass("hide-element");
        }

        sideNav.forceNarrow = !sideNav.forceNarrow;
    };

    app.showPlaylist = function () {
        var audioPlayer = document.querySelector("#audio-player");
        var playListView = document.querySelector("#playListView");
        var finalPlayList = [];
        if (audioPlayer.playlist == undefined) {
            var toast = document.querySelector("#toastMessage");
            toast.text = "Playlist is empty. Try adding songs to it.";
            toast.show();
        } else {
            for (var i = 0; i < audioPlayer.playlist.length; i++) {
                finalPlayList.push(audioPlayer.playlist[i].name);
            }
            playListView.playlist = finalPlayList;
            playListView.currentIndex = audioPlayer.currentIndex;
            playListView.toggleDialog();
            playListView.toggleCss(audioPlayer.currentIndex);
        }
    };


    app.switchView = function (e) {
        var identifier = e.target.parentElement.attributes.data.value;
        if (identifier !== undefined && identifier.trim().length > 0) {
            var album = document.querySelector("#boxList");
            var song = document.querySelector("#songList");

            if (identifier == "album") {

            } else if (identifier == "song") {

            }
        }

        var pages = document.querySelector('iron-pages');
        pages.selectNext();
    }

})(document);
