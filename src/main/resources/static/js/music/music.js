 jQuery(document).ready(function ($) {
        //return;


        var lab = '';

        var val = 0;
        var use_spectrum = 'off';

        var design_color_bg = 'b68160';
        var comments_enable = 'off';
        var design_color_prog = '4fd2c6,4fd2c6';

        var skinwave_wave_mode_canvas_mode = 'normal';
        var disable_volume = 'off';


        var bar_len = 3;
        var bar_space = 1;
        var reflection_size = 0.25;

        var init_parallaxer = false;

        //console.info(window);
        //console.info(get_query_arg(window.location.href, 'spectrum'));
        lab = 'spectrum';
        if(get_query_arg(window.location.href, lab)=='on'){
            use_spectrum = 'on';
            //console.info('ceva');
            $('a[title="waveform"]').removeClass('active');
            $('a[title="with dynamic spectrum"]').addClass('active');
        }



        lab = 'skinwave_layout_demo';
        val = 'demo1';
        if(get_query_arg(window.location.href, lab)){

            val = (get_query_arg(window.location.href, lab));





            $('*[data-label*="'+lab+'"]').each(function(){
                var _t = $(this);
//                console.info(_t, _t.attr('data-value'));

//                console.info(_t.attr('data-value'));
                console.info(val, _t.attr('data-value'));
                if(_t.attr('data-value')==val){
                    _t.parent().parent().find('.active').removeClass('active');
                    _t.addClass('active');
                }
            })
        }

//        val = 'demo1';
        var val_demo = val;

        var _c = $('#ap1');
        if(val=='demo1'){


            comments_enable = 'on';

//            console.info($('.preview-css'));
            $('.preview-css').append('')
            $('.overlay-comments').css('top', '10px')

        }



        if(val=='demo2'){

//            console.info($('.preview-css'));

            design_color_bg = '777777,999999';
            design_color_prog = '#962c3e,ea8c52';
            bar_space = 1;
            bar_len = 200;

            use_spectrum = 'on';
            init_parallaxer = true;


            $('.mcon-maindemo .divimage').eq(0).attr('data-src','img/bg_demo_2.jpg')
            $('#preview-buttons-colors-field').parent().hide();

            setTimeout(function(){

                console.info('$(\'.btn-itunes\') - ',$('.btn-itunes'));
                $('.btn-itunes').hide();
            },1000);
            $('#preview-buttons-colors-field').val(design_color_prog);
            setTimeout(function(){

                $('#preview-buttons-colors-field').trigger('change');
            },100)

            _c.attr('data-thumb','img/thumb_demo2.jpg');


            _c.removeClass('alternate-layout ');
            _c.removeClass('button-aspect-noir');
            _c.addClass('theme-light');

        }

        var val_skinwave_mode = 'default';
        if(val=='demo3'){

            design_color_bg = 'dddddd';
            design_color_prog = '#4e7596';
            val_skinwave_mode = 'small';
            bar_space = 2;

            init_parallaxer = true;

            $('#preview-buttons-colors-field').parent().hide();
            $('#preview-buttons-colors-field').val(design_color_prog);
            setTimeout(function(){

                $('#preview-buttons-colors-field').trigger('change');
            },100)

//            console.info($('.preview-css'));

            _c.attr('data-thumb','img/thumb_demo3.jpg');
            _c.addClass('button-aspect-noir theme-light');
            _c.removeClass('button-aspect-noir--filled');
           
        }
        if(val=='demo4'){

            design_color_bg = '999999,ffffff';
            design_color_prog = 'ff657a,ffffff';
            val_skinwave_mode = 'nocontrols';
            skinwave_wave_mode_canvas_mode = 'reflecto';
            disable_volume = 'on';
//            use_spectrum = 'on';
//            reflection_size = '0.5';

//            console.info($('.preview-css'));
            setTimeout(function(){

              
            },100)



            $('#preview-buttons-colors-field').parent().hide();
            $('#preview-buttons-colors-field').val(design_color_prog);
            setTimeout(function(){

                $('#preview-buttons-colors-field').trigger('change');
            },100)

            _c.attr('data-thumb','');
            _c.find('.meta-artist').eq(0).html('');
            _c.addClass('button-aspect-noir theme-light');
            _c.removeClass('button-aspect-noir--filled');

        }
        if(val=='demo5'){

            design_color_bg = '999999,ffffff';
            design_color_prog = 'd2ff52';
            val_skinwave_mode = 'small';
            reflection_size = 0;
            disable_volume = 'on';

            init_parallaxer = true;

//            console.info($('.preview-css'));
            setTimeout(function(){

            },100)



            $('#preview-buttons-colors-field').parent().hide();
            $('#preview-buttons-colors-field').val(design_color_prog);
            setTimeout(function(){

                $('#preview-buttons-colors-field').trigger('change');
            },100)
            $('.mcon-maindemo .divimage').eq(0).attr('data-src','img/grid2.jpg').css('filter','blur(15px)');

            _c.attr('data-thumb','');
            _c.attr('data-wrapper-image','img/grid2.jpg');
            _c.prepend('');
//            _c.find('.meta-artist').eq(0).html('');
            $('.preseter').hide();
            _c.addClass('button-aspect-noir theme-light zoomsounds-wrapper-bg-bellow disable-volume');
            _c.removeClass('button-aspect-noir--filled');

        }
        if(val=='demo6'){

            design_color_bg = '444444';
            design_color_prog = 'E5624B,E0924A';
            val_skinwave_mode = 'alternate';
            reflection_size = 0.25;
            bar_space = 0;
            bar_len = 1;
            disable_volume = 'on';

            init_parallaxer = true;

//            console.info($('.preview-css'));
            setTimeout(function(){

                $('.overlay-comments').hide();
            },100)



            $('#preview-buttons-colors-field').parent().hide();
            $('#preview-buttons-colors-field').val(design_color_prog);
            setTimeout(function(){

                $('#preview-buttons-colors-field').trigger('change');
            },100)
            $('.mcon-maindemo .divimage').eq(0).attr('data-src','img/bg_demo6.jpg');

            _c.attr('data-thumb','');
//            _c.find('.meta-artist').eq(0).html('');
            $('.preseter').hide();
            _c.addClass('button-aspect-noir  ');
            _c.removeClass('button-aspect-noir--filled');

        }


        var str_rate = '    ';
        var str_rate2 = '';
        var str_views = '';




        if(get_query_arg(window.location.href, 'rating')=='rates'){
            str_rate = '';
            //console.info('ceva');
          


            str_rate2 = '<div class="counter-rates"><span class="the-number">154</span> rates</div>';

            $('.option-selecter-object[data-label*="rating-"]').each(function(){
                var _t = $(this);
//                console.info(_t, _t.attr('data-value'));
                if(_t.attr('data-value')=='rates'){
                    _t.parent().children().removeClass('active');
                    _t.addClass('active');
                }
            })
        }

//        console.info(get_query_arg(window.location.href, 'rating'))
        if(get_query_arg(window.location.href, 'rating')=='off'){
            str_rate = '';
            str_rate2 = '';

            $('.option-selecter-object[data-label*="rating-"]').each(function(){
                var _t = $(this);
//                console.info(_t, _t.attr('data-value'));
                if(_t.attr('data-value')=='off'){
                    _t.parent().children().removeClass('active');
                    _t.addClass('active');
                }
            })
        }

        if(get_query_arg(window.location.href, 'disable_views')=='on'){
            str_views = '';
            $('input[name="disable_views"]').prop('checked', true);
        }

        var disable_scrub = 'default';

        if(get_query_arg(window.location.href, 'disable_scrub')=='on'){
            disable_scrub = 'on';
            $('input[name="disable_scrub"]').prop('checked', true);
        }




//        console.info(get_query_arg(window.location.href, 'disable_volume'))
        if(get_query_arg(window.location.href, 'disable_volume')=='on'){
            disable_volume = 'on';
            $('input[name="disable_volume"]').prop('checked', true);
        }

        var skinwave_mode = 'normal';
        var skinwave_wave_mode = 'canvas';

        lab = 'skinwave-mode'

        if(val_skinwave_mode!='default'){
            skinwave_mode = val_skinwave_mode;
        }
        if(get_query_arg(window.location.href,lab )){
            val = get_query_arg(window.location.href,lab );

//            console.info('val_skinwave_mode - ',val_skinwave_mode);
            if(val_skinwave_mode!='default'){
                val = val_skinwave_mode;
            }

//            console.info(lab, ' - ',val);
            skinwave_mode = val;



            $('.option-selecter-object[data-label="'+lab+'"]').each(function(){
                var _t = $(this);
//                console.info(_t, _t.attr('data-value'));
                if(_t.attr('data-value')==val){
                    _t.parent().children().removeClass('active');
                    _t.addClass('active');
                }
            })
        }
        if(skinwave_mode=='small'){
            _c.removeClass('alternate-layout');
        }


        lab= 'skinwave-number';
        if(get_query_arg(window.location.href, lab )){

            bar_len = parseInt(get_query_arg(window.location.href, lab), 10);

            if(bar_len == 900){
                bar_space = 0;
                bar_len = 1;
            }

            val = parseInt(get_query_arg(window.location.href, lab), 10);

            console.info(lab, ' - ',val, 'bar_len - ',bar_len);


            $('*[data-label="'+lab+'"]').each(function() {
                var _t = $(this);


                console.info(_t, val);
                _t.removeClass('active');
                if (_t.attr('data-value') == val){

                    _t.addClass('active');
                }
            })
        }

        lab= 'skinwave_wave_mode';
        if(get_query_arg(window.location.href,lab )){

            skinwave_wave_mode = (get_query_arg(window.location.href, lab));

            $('*[data-label="'+lab+'"]').each(function() {
                var _t = $(this);
                _t.removeClass('active');
                if (_t.attr('data-value') == skinwave_wave_mode){

                    _t.addClass('active');
                }
            })
        }



        if(get_query_arg(window.location.href, 'reflection_size')){

            reflection_size = parseFloat(get_query_arg(window.location.href, 'reflection_size'));



        }





        $('#preview-buttons-colors-field').bind('change', function(){
            var _t = $(this);
            if(val_demo!='demo333'){

                var aux = '.audioplayer.skin-wave .btn-embed-code, body .audioplayer.skin-wave .volume_active, body .audioplayer.skin-wave .volume_active{ background-color: '+_t.val()+'; }';

                aux += 'body .audioplayer.skin-wave .meta-artist .the-artist{ color: '+_t.val()+'; } ';


                if(val_demo=='demo2'){

                    aux += '.audioplayer.skin-wave .ap-controls .con-playpause .playbtn,.audioplayer.skin-wave .ap-controls .con-playpause .pausebtn{ background-color: '+_t.val()+'; } ';
                }

                $("#preview-buttons-colors").html(aux);


                if(document.getElementById("ap1") && document.getElementById("ap1").api_change_design_color_highlight){
                    var val = String(_t.val()).replace('#','');
//                document.getElementById("ap1").api_change_design_color_highlight('ffffff,000000');
                    document.getElementById("ap1").api_change_design_color_highlight(val);
                }
            }


        })





        //<iframe src="http://localhost/wordpress/wp-content/plugins/dzs-zoomsounds/bridge.html?type=gallery&id=gal1" width="100%" height="" style="overflow:hidden; transition: height 0.5s ease-out;" scrolling="no" frameborder="0"></iframe>


//        console.info(str_rate, str_views, str_rate2)

        var settings_ap = {
            disable_volume: disable_volume
            ,disable_scrub: disable_scrub
            ,design_skin: 'skin-wave'
            ,skinwave_dynamicwaves:'on'
            ,skinwave_enableSpectrum:use_spectrum
            ,settings_backup_type:'full'
            ,settings_useflashplayer:'auto'
            ,skinwave_spectrummultiplier: '4'
            ,design_color_bg: design_color_bg
            ,skinwave_comments_enable:comments_enable
            ,skinwave_comments_playerid:'3'
            ,skinwave_mode: skinwave_mode
            ,autoplay: 'on'
            ,embed_code:'You can enable embed button for your viewers to embed on their site, the code will auto generate. &lt;iframe src=&quot;http://yoursite.com/bridge.html?type=gallery&amp;id=gal1&quot; &gt;&lt;/iframe&gt; <span class="copy-embed-code-btn"><i class="fa fa-clipboard"></i> Copy Embed</span> '
            ,settings_extrahtml:str_rate+str_views+str_rate2
            ,skinwave_wave_mode: skinwave_wave_mode
            ,skinwave_wave_mode_canvas_waves_number: bar_len
            ,skinwave_wave_mode_canvas_waves_padding: bar_space
            ,skinwave_wave_mode_canvas_reflection_size: reflection_size
            ,design_wave_color_progress: design_color_prog // == light or full
            ,design_animateplaypause: "on"
            ,skinwave_wave_mode_canvas_mode: skinwave_wave_mode_canvas_mode
        };
        console.info(settings_ap);
        dzsap_init('#ap1',settings_ap);


        if(init_parallaxer){


            $('.mcon-maindemo').children('.divimage').fadeIn('fast');


            if(val_demo!='demo6'){

                $('.mcon-maindemo').children('.divimage,.grey-overlay,.semi-black-overlay').fadeIn('fast');
            }

            $.getScript( "dzsparallaxer/dzsparallaxer.js" )
                .done(function( script, textStatus ) {

                    console.info('loaded script');
                    window.dzsprx_init('.dzsparallaxer',{
                        init_each:true
                    })
                })
                .fail(function( jqxhr, settings, exception ) {
                    $( "div.log" ).text( "Triggered ajaxError handler." );
                });


            $('head').append('<link rel="stylesheet" type="text/css" href="static/css/dzsparallaxer_1.css"/>');

            setTimeout(function(){

            },500);
        }
    });



    function get_query_arg(purl, key){
//        if(key!='disable_volume'){ return; };

        if(purl.indexOf(key+'=')>-1){
            //faconsole.log('testtt');
            var regexS = "[?&]"+key + "=.+";
            var regex = new RegExp(regexS);
            var regtest = regex.exec(purl);
//            console.info(key, regtest);

            if(regtest != null){
                var splitterS = regtest[0];
                if(splitterS.indexOf('&')>-1){
                    var aux = splitterS.split('&');
//                    console.info(aux)
                    if(aux[0].indexOf('?')>-1){
                        splitterS = aux[0];
                    }else{
                        splitterS = aux[1];
                    }

//                    console.info(splitterS);

                }
//                console.log(splitterS);
                var splitter = splitterS.split('=');
//                console.log(splitter[1]);
                //var tempNr = ;

                return splitter[1];

            }
            //$('.zoombox').eq
        }
    }



    function add_query_arg(purl, key,value){
        key = encodeURIComponent(key); value = encodeURIComponent(value);

        var s = purl;
        var pair = key+"="+value;

        var r = new RegExp("(&|\\?)"+key+"=[^\&]*");

        s = s.replace(r,"$1"+pair);
        //console.log(s, pair);
        if(s.indexOf(key + '=')>-1){


        }else{
            if(s.indexOf('?')>-1){
                s+='&'+pair;
            }else{
                s+='?'+pair;
            }
        }
        //if(!RegExp.$1) {s += (s.length>0 ? '&' : '?') + kvp;};

        return ;
    }

