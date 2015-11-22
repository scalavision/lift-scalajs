/*
 * The purpose of this script is to autorefresh browser
 * when the server has rebooted.
 *
 * In Boot.scala under Props mode, if RunMode is Development
 * the file TimeStamp will be written to, in this regard
 * the serve-proxy task will be watching, hence reload the browser
 *
 * This script can be running along with gulpscript in the client
 * project
 */

var gulp = require('gulp');
var gutil = require('gulp-util');
var browserSync = require('browser-sync');
var reload = browserSync.reload;

var webapp = '../../web/src/main/webapp';
var jsUtils = webapp + '/js/**/*.js';
var scalaJsClient = '../../web/target/scala-2.11';

var fastOptJS = scalaJsClient + '/lift-scalajs-webclient-fastopt.js';
var jsDeps = scalaJsClient + '/lift-scalajs-webclient-jsdeps.js'; 
var jsMap = scalaJsClient + '/lift-scalajs-webclient-fastopt.map';

var serverSrc = '../src/main/webapp';
var serverSrcJs = serverSrc + '/js';
var timeStamp = '../../Timestamp';

gulp.task('serve-proxy', function() {
  browserSync({
    proxy: 'localhost:8080',
    port: 4000
  });
  gulp.watch(timeStamp).on('change', reload);
});

gulp.task('copy-js-src', function(){
 gulp.src(fastOptJS)
   .pipe(gulp.dest(serverSrcJs));
 gulp.src(jsMap)
   .pipe(gulp.dest(serverSrcJs));
});

gulp.task('copy-js-lib', function() {
  gulp.src(jsDeps)
    .pipe(gulp.dest(serverSrcJs));
});

gulp.task('copy-js-utils', function() {
  gulp.src(jsUtils)
    .pipe(gulp.dest(serverSrcJs));
});

gulp.task('init-sources', ['copy-js-src','copy-js-lib', 'copy-js-utils'], function() {
  return  gutil.log('application initialized');  
});

gulp.task('default', ['init-sources', 'serve-proxy'], function () {
  return gutil.log('Gulp is running, proxy: localhost:8080 to localhost:4000');
});
