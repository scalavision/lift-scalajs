/*
 * This build script serve the following purposes:
 * - run a standalone version of the scalajs webclient (no server)
 * - copy every change into the webapp folder of the server project
 * - autorefresh the browser on every change on the client side
 *
 * In order to make the client work standalone, without a server,
 * there is a mocking script for the server api in mockup-api folder.
 *
 * jsMockup is referencing this path
 *
 * This build script can run alongside the gulp build script in server
 * but it most probably will refresh the browser at least twice
 */

var gulp = require('gulp');
var gutil = require('gulp-util');
var browserSync = require('browser-sync');
var reload = browserSync.reload;

var webapp = '../src/main/webapp';
var jsUtils = webapp + '/js/**/*.js';
var jsMockup = webapp + '/mockup-api-js/**/*.js';
var fonts = webapp + '/fonts/**/*.*';
var css = webapp + '/styles/**/*.css';
var target= '../target/scala-2.11';

var fastOptJS = target + '/lift-scalajs-webclient-fastopt.js';
var jsDeps = target + '/lift-scalajs-webclient-jsdeps.js'; 
var jsMap = target + '/lift-scalajs-webclient-fastopt.js.map';

var serverSrc = '../../server/src/main/webapp';
var serverSrcJs = serverSrc + '/js';

// serving the file locally, directly with browserSync
gulp.task('serve-local', function() {
 browserSync({
 // files: 'src/main/webapp/js/*.js',
  server: {
      baseDir: '../',
      index: 'src/main/webapp/index.html'
    } 
  });

  gulp.watch([
    webapp + '/index.html', 
    fastOptJS, 
    jsDeps, 
    jsMockup])
     .on('change', reload);
});

/*
gulp.task('serve-proxy', function() {
  browserSync({
    proxy: 'localhost:8080',
    port: 3000
  });

  gulp.watch(fastOptJS, ['copy-js-src']);
  gulp.watch(jsDeps, ['copy-js-lib']);

});
*/

gulp.task('autoupdate-server', ['init-sources'], function(){
  gulp.watch(fastOptJS, ['copy-js-src']);
  gulp.watch(jsDeps, ['copy-js-lib']);
  gulp.watch(jsUtils, ['copy-js-utils']);
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

gulp.task('default', ['init-sources', 'serve-local'], function () {
  gulp.watch(fastOptJS, ['copy-js-src']);
  gulp.watch(jsDeps, ['copy-js-lib']);
  gulp.watch(jsUtils, ['copy-js-utils']);
  return gutil.log('Gulp is running');
});
