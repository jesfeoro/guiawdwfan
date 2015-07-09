window.carousel_header = {
    links: [],
    _position: 0,
    prev: function () {
        if (this._position == 0) {
            this._position = this.links.length;
        }
        this._position--;
        this.go();
    },
    next: function () {
        if (this._position == this.links.length - 1) {
            this._position = -1;
        }
        this._position++;
        this.go();
    },
    _initImages: function (imgHolder) {
        var imgHolder = document.getElementById("carousel_active_href");
        var controls = document.getElementById("carousel_controls");
       // controls.style.width = 19 * this.links.length + "px";
        for (var i = 0; i < this.links.length; i++) {
            var img = document.createElement("img");
            img.src = this.links[i].src;
            img.alt = this.links[i].alt || "";
            img.title = this.links[i].alt || "";
            img.style.display = "none";
            imgHolder.appendChild(img);

            var button = document.createElement("div");
            button.className = "control";
            controls.appendChild(button);
        }


    },
    autoswitch: function () {
        if (this._timeout)
            clearTimeout(this._timeout);
        var self = this;
        this._timeout = setTimeout(function () {
            self.next();
        }, 15 * 1000);
    },
    init: function (config) {
        if (config) {
            this.links = config;
        }
        this.go();
    },
    go: function (index) {
        if (index === undefined) {
            index = this._position;
        } else {
            this._position = index;
        }

        var link = this.links[index];
        var imgHolder = document.getElementById("carousel_active_href");
        imgHolder.href = link.href;

        var items = imgHolder.getElementsByTagName("img");
        if (items.length < this.links.length) {
            this._initImages();
        }

        for (var i = 0; i < items.length; i++) {
            if (i != index) {
                items[i].style.display = "none";
            } else {
                items[i].style.display = "";
            }
        }
        var self = this;
        setTimeout(function () {
            self._updatePointers(index)
        }, 1);
        this.autoswitch();

    },
    _updatePointers: function (index) {
        if (index === undefined) {
            index = this._position;
        }
        var cts = document.getElementById("carousel_controls").getElementsByTagName("div");
        for (var i = 0; i < cts.length; i++) {
            if (i != index) {
                cts[i].className = cts[i].className.replace(/ active/g, "");

                (function (temp) {
                    cts[temp].onclick = function () { carousel_header.go(temp) };
                })(i)

            } else {
                cts[i].className += " active";
                cts[i].onclick = null;
            }
        }

    }

};

window.popupTypes = { bug: 0, request: 1, feedback:2 };
function getFeedback(type) {

    var options = [
        { value:"bug_report", label:"Report a bug" },
        { value:"feature_request", label:"Request a feature" },
        { value:"feedback", label:"Leave a feedback"}];
    var opts = "";
    for (var i = 0; i < options.length; i++) {
        opts += ["<option value=\"",  options[i].value ,"\"", (i===type ? " selected " : ""), ">", options[i].label, "</option>"].join('');
    }


    var box = window.temp_box = dhtmlx.modalbox({
        title: "Contact Us",
        text: "<div id='form_in_box'>" +
                "<div class=\"form-block\"><div class=\"box_label\"> Email </div><div class=\"box_input\"><input title=\"Enter your name or email address\" name=\"author\" type='text'></div></div>" +
                "<div class=\"form-block\"><div class=\"box_label\"> Subject </div><div class=\"box_input\"><select name=\"type\" type='text'>" + opts + "</select></div></div>" +
                "<div class=\"form-block\"><div class=\"box_label\"> Summary </div><div class=\"box_input\"><textarea rows=\"4\"></textarea></div></div>" +
                "<div class=\"form-block \"><span class=\"dhtmlx_button\"><input type=\"button\" onclick=\"hideBox(this, true)\" value=\"Send\" style=\"background-color: #6fbb01 !important;color: white;border-color: #6fbb01 !important;\"/></span><span class=\"dhtmlx_button\"><input type=\"button\"  onclick=\"hideBox(this, false)\" value=\"Cancel\"/></span></div>",
        width: "450px",
        ok: "Send",
        type:"contact_us_form",
        cancel: "Cancel"
    });
    if (document.attachEvent)
        document.attachEvent("onkeydown", contact_mod_key);
    else
        document.addEventListener("keydown", contact_mod_key, true);

    //setTimeout(function () {
    //    box.getElementsByTagName("input")[0].focus();
    //}, 1);
}
function contact_mod_key(e) {

        e = e || event;
        var code = e.which || event.keyCode;
  
        if (code == 27) {
            hideBox(window.temp_box);
        }
    }
    
function hideBox(box, result) {
    if (result) {       

        while (box.parentNode && box.id != "form_in_box") {
            box = box.parentNode;
        }
        if (box) {
            var authBox = box.getElementsByTagName("input")[0];
            var contentBox = box.getElementsByTagName("textarea")[0];

            var author = authBox.value;
            var type = box.getElementsByTagName("select")[0].value;
            var content = contentBox.value;

            var error_color = "#FBDBDB";

            if (!(author && content)) {
                if (!author) {
                    authBox.style.backgroundColor = error_color;
                }
                if (!content) {
                    contentBox.style.backgroundColor = error_color;
                }
                return;
            }

            contentBox.style.backgroundColor = "";
            authBox.style.backgroundColor = "";

            var params = ["author=" + author, "type=" + type, "message=" + content, "page=" + window.location.href].join("&");
            sendRequest("/common/contact.ashx", params, "POST");
        }
        dhtmlx.message("Thank you for your feedback!");
    }
    
    if (document.attachEvent)
        document.detachEvent("onkeydown", contact_mod_key);
    else
        document.removeEventListener("keydown", contact_mod_key, true);
    window.temp_box = null;
    dhtmlx.modalbox.hide(box);
    

}


function sendRequest(url, params, type) {
    var req;
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    }
    else {
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    req.onreadystatechange = function () { }
    req.open(type, url, true);
    if (type == "POST") {
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    }
    req.send(params || null);
}


// popup search

function showSearch() {
    document.getElementById("search-popup").style.display = "block";
}

function hideSearch() {
    document.getElementById("search-popup").style.display = "none";
}

// to top move

(function () {

    function $(id) {
        return document.getElementById(id);
    }

    window.goTop = function(acceleration, time) {
        acceleration = acceleration || 0.1;
        time = time || 12;

        var dx = 0;
        var dy = 0;
        var bx = 0;
        var by = 0;
        var wx = 0;
        var wy = 0;

        if (document.documentElement) {
            dx = document.documentElement.scrollLeft || 0;
            dy = document.documentElement.scrollTop || 0;
        }
        if (document.body) {
            bx = document.body.scrollLeft || 0;
            by = document.body.scrollTop || 0;
        }
        var wx = window.scrollX || 0;
        var wy = window.scrollY || 0;

        var x = Math.max(wx, Math.max(bx, dx));
        var y = Math.max(wy, Math.max(by, dy));

        var speed = 1 + acceleration;
        window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));
        if (x > 0 || y > 0) {
            window.setTimeout(function(){
                goTop(acceleration, time);
            }, time);
        }
        return false;
    }


})();


/**
* String.trim polyfill from here
* https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
**/
if (!String.prototype.trim) {
    String.prototype.trim = function () {
        // Make sure we trim BOM and NBSP
        rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
        return this.replace(rtrim, "");
    }
}