/**
 * 
 * Find more about the scrolling function at
 * http://cubiq.org/iscroll
 *
 * Copyright (c) 2010 Matteo Spinelli, http://cubiq.org/
 * Released under MIT license
 * http://cubiq.org/dropbox/mit-license.txt
 * 
 * Version 3.6beta1 - Last updated: 2010.08.10
 * 
 */

(function(){
	
	
function setWebkitTransform(node, translate){
//	node.style.webkitTransform = (node.style.webkitTransform||"")	
//			.replace(/translate.*?\)/, "")
//			.replace(/\s+/g, " ")
//			.trim()
//			+ " " + translate;
	node.style.webkitTransform = translate;
}

function iScroll (el, options) {
	var that = this, i;
	that.element = typeof el == 'object' ? el : document.getElementById(el);
	that.wrapper = that.element.parentNode;

	that.element.style.webkitTransitionProperty = '-webkit-transform';
	that.element.style.webkitTransitionTimingFunction = 'cubic-bezier(0,0,0.25,1)';
	that.element.style.webkitTransitionDuration = '0';
	setWebkitTransform(that.element, translateOpen + '0,0' + translateClose);

	// Default options
	that.options = {
		bounce: has3d,
		momentum: has3d,
		checkDOMChanges: true,
		topOnDOMChanges: false,
		hScrollbar: has3d,
		vScrollbar: has3d,
		fadeScrollbar: isIphone || isIpad || !isTouch,
		shrinkScrollbar: isIphone || isIpad,
		desktopCompatibility: false,
		overflow: 'auto',
		position: 'relative',
		snap: false,
		touchCount: 0,
		stopEvents: true
	};
	
	// User defined options
	if (typeof options == 'object') {
		for (i in options) {
			that.options[i] = options[i];
		}
	}

	if (that.options.desktopCompatibility) {
		that.options.overflow = 'hidden';
	}

	that.wrapper.style.overflow = that.options.overflow;
	that.wrapper.style.position = that.options.position;
	
	that.refresh();

	window.addEventListener('onorientationchange' in window ? 'orientationchange' : 'resize', that, false);

	if (isTouch || that.options.desktopCompatibility) {
		that.element.addEventListener(START_EVENT, that, false);
		that.element.addEventListener(MOVE_EVENT, that, false);
		that.element.addEventListener(END_EVENT, that, false);
	}
	
	if (that.options.checkDOMChanges) {
		that.element.addEventListener('DOMSubtreeModified', that, false);
	}
	
	if (!isTouch) {
		that.element.addEventListener('click', that, true);
	}
}

iScroll.prototype = {
	x: 0,
	y: 0,
	dist: 0,

	handleEvent: function (e) {
		var that = this;

		switch (e.type) {
			case 'click':
				if (!e._fake) {
					e.stopPropagation();
				}
				break;
			case START_EVENT:
				that.touchStart(e);
				break;
			case MOVE_EVENT:
				that.touchMove(e);
				break;
			case END_EVENT:
				that.touchEnd(e);
				break;
			case 'webkitTransitionEnd':
				that.transitionEnd();
				break;
			case 'orientationchange':
			case 'resize':
				that.refresh();
				break;
			case 'DOMSubtreeModified':
				that.onDOMModified();
				break;
		}
	},
	
	onDOMModified: function () {
		var that = this;

		that.refresh();

		if (that.options.topOnDOMChanges && (that.x!=0 || that.y!=0)) {
			that.scrollTo(0,0,'0');
		}
	},

	refresh: function () {
		var that = this,
			resetX = this.x, resetY = this.y,
			snap;
		
		that.scrollWidth = that.wrapper.clientWidth;
		that.scrollHeight = that.wrapper.clientHeight;
		that.scrollerWidth = that.wrapper.scrollWidth;
		that.scrollerHeight = that.wrapper.scrollHeight;
		that.maxScrollX = that.scrollWidth - that.scrollerWidth;
		that.maxScrollY = that.scrollHeight - that.scrollerHeight;
		that.directionX = 0;
		that.directionY = 0;

		if (that.scrollX) {
			if (that.maxScrollX >= 0) {
				resetX = 0;
			} else if (that.x < that.maxScrollX) {
				resetX = that.maxScrollX;
			}
		}
		if (that.scrollY) {
			if (that.maxScrollY >= 0) {
				resetY = 0;
			} else if (that.y < that.maxScrollY) {
				resetY = that.maxScrollY;
			}
		}
		// Snap
		if (that.options.snap) {
			that.maxPageX = -Math.floor(that.maxScrollX/that.scrollWidth);
			that.maxPageY = -Math.floor(that.maxScrollY/that.scrollHeight);

			snap = that.snap(resetX, resetY);
			resetX = snap.x;
			resetY = snap.y;
		}

		if (resetX!=that.x || resetY!=that.y) {
			that.setTransitionTime('0');
			that.setPosition(resetX, resetY, true);
		}
		
		that.scrollX = that.scrollerWidth > that.scrollWidth;
		that.scrollY = !that.scrollX || that.scrollerHeight > that.scrollHeight;
		
		// Update horizontal scrollbar
		if (that.options.hScrollbar && that.scrollX) {
			that.scrollBarX = that.scrollBarX || new scrollbar('horizontal', that.wrapper, that.options.fadeScrollbar, that.options.shrinkScrollbar);
			that.scrollBarX.init(that.scrollWidth, that.scrollerWidth);
		} else if (that.scrollBarX) {
			that.scrollBarX = that.scrollBarX.remove();
		}

		// Update vertical scrollbar
		if (that.options.vScrollbar && that.scrollY && that.scrollerHeight > that.scrollHeight) {
			that.scrollBarY = that.scrollBarY || new scrollbar('vertical', that.wrapper, that.options.fadeScrollbar, that.options.shrinkScrollbar);
			that.scrollBarY.init(that.scrollHeight, that.scrollerHeight);
		} else if (that.scrollBarY) {
			that.scrollBarY = that.scrollBarY.remove();
		}
	},

	setPosition: function (x, y, hideScrollBars) {
		var that = this;
		
		that.x = x;
		that.y = y;

		setWebkitTransform(that.element, translateOpen + that.x + 'px,' + that.y + 'px' + translateClose);

		// Move the scrollbars
		if (!hideScrollBars) {
			if (that.scrollBarX) {
				that.scrollBarX.setPosition(that.x);
			}
			if (that.scrollBarY) {
				that.scrollBarY.setPosition(that.y);
			}
		}
	},
	
	setTransitionTime: function(time) {
		var that = this;
		
		time = time || '0';
		that.element.style.webkitTransitionDuration = time;
		
		if (that.scrollBarX) {
			that.scrollBarX.bar.style.webkitTransitionDuration = time;
			that.scrollBarX.wrapper.style.webkitTransitionDuration = has3d && that.options.fadeScrollbar ? '300ms' : '0';
		}
		if (that.scrollBarY) {
			that.scrollBarY.bar.style.webkitTransitionDuration = time;
			that.scrollBarY.wrapper.style.webkitTransitionDuration = has3d && that.options.fadeScrollbar ? '300ms' : '0';
		}
	},
		
	touchStart: function(e) {
		
		if (e.target && ["textarea", "input"].include(String(e.target.tagName).toLowerCase())){
			return;
		}
		
		
		var that = this,
			matrix;

		if (that.options.touchCount && e.touches.length !== that.options.touchCount){
			if (that.scrolling){
				that.resetPosition();
			}
			that.scrolling = false;
			that.moved = false;
			return;
		}
		
		that.scrolling = true;		// This is probably not needed, but may be useful if iScroll is used in conjuction with other frameworks
		
		if (that.options.stopEvents){
			e.preventDefault();
			e.stopPropagation();
		}

		that.moved = false;
		that.dist = 0;

		that.setTransitionTime('0');

		// Check if the scroller is really where it should be
		if (that.options.momentum || that.options.snap) {
			matrix = new WebKitCSSMatrix(window.getComputedStyle(that.element).webkitTransform);
			if (matrix.e != that.x || matrix.f != that.y) {
				document.removeEventListener('webkitTransitionEnd', that, false);
				that.setPosition(matrix.e, matrix.f);
				that.moved = true;
			}
		}

		that.touchStartX = isTouch ? e.touches[0].pageX : e.pageX;
		that.scrollStartX = that.x;

		that.touchStartY = isTouch ? e.touches[0].pageY : e.pageY;
		that.scrollStartY = that.y;

		that.scrollStartTime = e.timeStamp;

		that.directionX = 0;
		that.directionY = 0;
	},
	
	touchMove: function(e) {
		var that = this,
			pageX = isTouch ? e.touches[0].pageX : e.pageX,
			pageY = isTouch ? e.touches[0].pageY : e.pageY,
			leftDelta = that.scrollX ? pageX - that.touchStartX : 0,
			topDelta = that.scrollY ? pageY - that.touchStartY : 0,
			newX = that.x + leftDelta,
			newY = that.y + topDelta;

		if (!that.scrolling) {
			return;
		}

//		e.preventDefault();
//		e.stopPropagation();

		that.dist+= Math.abs(that.touchStartX - pageX) + Math.abs(that.touchStartY - pageY);

		that.touchStartX = pageX;
		that.touchStartY = pageY;

		// Slow down if outside of the boundaries
		if (newX > 0 || newX < that.maxScrollX) { 
			newX = that.options.bounce ? Math.round(that.x + leftDelta / 3) : newX >= 0 ? 0 : that.maxScrollX;
		}
		if (newY > 0 || newY < that.maxScrollY) { 
			newY = that.options.bounce ? Math.round(that.y + topDelta / 3) : newY >= 0 ? 0 : that.maxScrollY;
		}

		if (that.dist > 5) {			// 5 pixels threshold is needed on Android, but also on iPhone looks more natural
			that.setPosition(newX, newY);
			that.moved = true;
			that.directionX = leftDelta > 0 ? -1 : 1;
			that.directionY = topDelta > 0 ? -1 : 1;
		}
	},
	
	touchEnd: function(e) {
		var that = this,
			time = e.timeStamp - that.scrollStartTime,
			target, ev,
			momentumX, momentumY,
			newDuration = 0,
			newPositionX = that.x, newPositionY = that.y,
			snap;

		if (!that.scrolling) {
			return;
		}
		

		that.scrolling = false;
		
		if (!that.moved) {
			that.resetPosition();

			// Find the last touched element
			target = isTouch ? e.changedTouches[0].target : e.target;
			while (target.nodeType != 1) {
				target = target.parentNode;
			}

			// Create the fake event
			ev = document.createEvent('Event');
			ev.initEvent('focus', true, true);
			target.dispatchEvent(ev);
			
			//ev = document.createEvent('MouseEvents');
			//ev.initMouseEvent("click", true, true, e.view, 1,
			//	target.screenX, target.screenY, target.clientX, target.clientY,
			//	e.ctrlKey, e.altKey, e.shiftKey, e.metaKey,
			//	0, null);
			//ev._fake = true;
			//target.dispatchEvent(ev);
			
			return;
		}
		

		if (that.options.stopEvents){
			e.preventDefault();
			e.stopPropagation();
		}
		

		if (!that.options.snap && time > 250) {			// Prevent slingshot effect
			that.resetPosition();
			return;
		}

		if (that.options.momentum) {
			momentumX = that.scrollX === true
				? that.momentum(that.x - that.scrollStartX,
								time,
								that.options.bounce ? -that.x + that.scrollWidth/5 : -that.x,
								that.options.bounce ? that.x + that.scrollerWidth - that.scrollWidth + that.scrollWidth/5 : that.x + that.scrollerWidth - that.scrollWidth)
				: { dist: 0, time: 0 };

			momentumY = that.scrollY === true
				? that.momentum(that.y - that.scrollStartY,
								time,
								that.options.bounce ? -that.y + that.scrollHeight/5 : -that.y,
								that.options.bounce ? (that.maxScrollY < 0 ? that.y + that.scrollerHeight - that.scrollHeight : 0) + that.scrollHeight/5 : that.y + that.scrollerHeight - that.scrollHeight)
				: { dist: 0, time: 0 };

			newDuration = Math.max(Math.max(momentumX.time, momentumY.time), 1);		// The minimum animation length must be 1ms
			newPositionX = that.x + momentumX.dist;
			newPositionY = that.y + momentumY.dist;
		}
		
		if (that.options.snap) {
			snap = that.snap(newPositionX, newPositionY);
			newPositionX = snap.x;
			newPositionY = snap.y;
			newDuration = Math.max(snap.time, newDuration);
		}
/*
		if (newPositionX==that.x && newPositionY==that.y) {
			that.resetPosition();
			return;
		}
*/

		that.scrollTo(newPositionX, newPositionY, newDuration + 'ms');
	},

	transitionEnd: function () {
		var that = this;
		document.removeEventListener('webkitTransitionEnd', that, false);
		that.resetPosition();
	},

	resetPosition: function (time) {
		var that = this,
			resetX = that.x,
		 	resetY = that.y;

		if (that.x >= 0) {
			resetX = 0;
		} else if (that.x < that.maxScrollX) {
			resetX = that.maxScrollX;
		}

		if (that.y >= 0 || that.maxScrollY > 0) {
			resetY = 0;
		} else if (that.y < that.maxScrollY) {
			resetY = that.maxScrollY;
		}

		if (resetX != that.x || resetY != that.y) {
			that.scrollTo(resetX, resetY, time);
		} else {
			that.onScrollEnd();		// Execute custom code on scroll end
			
			// Hide the scrollbars
			if (that.scrollBarX) {
				that.scrollBarX.hide();
			}
			if (that.scrollBarY) {
				that.scrollBarY.hide();
			}
		}
	},
	
	snap: function (x, y) {
		var that = this, time;

		if (that.directionX > 0) {
			x = Math.floor(x/that.scrollWidth);
		} else if (that.directionX < 0) {
			x = Math.ceil(x/that.scrollWidth);
		} else {
			x = Math.round(x/that.scrollWidth);
		}
		that.pageX = -x;
		x = x * that.scrollWidth;
		if (x > 0) {
			x = that.pageX = 0;
		} else if (x < that.maxScrollX) {
			that.pageX = that.maxPageX;
			x = that.maxScrollX;
		}

		if (that.directionY > 0) {
			y = Math.floor(y/that.scrollHeight);
		} else if (that.directionY < 0) {
			y = Math.ceil(y/that.scrollHeight);
		} else {
			y = Math.round(y/that.scrollHeight);
		}
		that.pageY = -y;
		y = y * that.scrollHeight;
		if (y > 0) {
			y = that.pageY = 0;
		} else if (y < that.maxScrollY) {
			that.pageY = that.maxPageY;
			y = that.maxScrollY;
		}

		// Snap with constant speed (proportional duration)
		time = Math.round(Math.max(
				Math.abs(that.x - x) / that.scrollWidth * 500,
				Math.abs(that.y - y) / that.scrollHeight * 500
			));
			
		return { x: x, y: y, time: time };
	},

	scrollTo: function (destX, destY, runtime) {
		var that = this;

		if (that.x == destX && that.y == destY) {
			that.onScrollEnd();
			return;
		}

		that.setTransitionTime(runtime || '400ms');
		that.setPosition(destX, destY);

		if (runtime==='0' || runtime=='0s' || runtime=='0ms') {
			that.resetPosition();
			that.onScrollEnd();
		} else {
			document.addEventListener('webkitTransitionEnd', that, false);	// At the end of the transition check if we are still inside of the boundaries
		}
	},
	
	scrollToPage: function (pageX, pageY, runtime) {
		var that = this, snap;

		if (!that.options.snap) {
			that.pageX = -Math.round(that.x / that.scrollWidth);
			that.pageY = -Math.round(that.y / that.scrollHeight);
		}

		if (pageX == 'next') {
			pageX = ++that.pageX;
		} else if (pageX == 'prev') {
			pageX = --that.pageX;
		}

		if (pageY == 'next') {
			pageY = ++that.pageY;
		} else if (pageY == 'prev') {
			pageY = --that.pageY;
		}

		pageX = -pageX*that.scrollWidth;
		pageY = -pageY*that.scrollHeight;

		snap = that.snap(pageX, pageY);
		pageX = snap.x;
		pageY = snap.y;

		that.scrollTo(pageX, pageY, runtime || '500ms');
	},

	scrollToElement: function (el, runtime) {
		el = typeof el == 'object' ? el : this.element.querySelector(el);

		if (!el) {
			return;
		}

		var that = this,
			x = that.scrollX ? -el.offsetLeft : 0,
			y = that.scrollY ? -el.offsetTop : 0;

		if (x >= 0) {
			x = 0;
		} else if (x < that.maxScrollX) {
			x = that.maxScrollX;
		}

		if (y >= 0) {
			y = 0;
		} else if (y < that.maxScrollY) {
			y = that.maxScrollY;
		}

		that.scrollTo(x, y, runtime);
	},

	momentum: function (dist, time, maxDistUpper, maxDistLower) {
		var friction = 2.5,
			deceleration = 1.2,
			speed = Math.abs(dist) / time * 1000,
			newDist = speed * speed / friction / 1000,
			newTime = 0;

		// Proportinally reduce speed if we are outside of the boundaries 
		if (dist > 0 && newDist > maxDistUpper) {
			speed = speed * maxDistUpper / newDist / friction;
			newDist = maxDistUpper;
		} else if (dist < 0 && newDist > maxDistLower) {
			speed = speed * maxDistLower / newDist / friction;
			newDist = maxDistLower;
		}
		
		newDist = newDist * (dist < 0 ? -1 : 1);
		newTime = speed / deceleration;

		return { dist: Math.round(newDist), time: Math.min(Math.round(newTime), 10) };
	},
	
	onScrollEnd: function () {},

	destroy: function (full) {
		var that = this;
		
		window.removeEventListener('orientationchange', that, false);
		window.removeEventListener('resize', that, false);
		that.element.removeEventListener(START_EVENT, that, false);
		that.element.removeEventListener(MOVE_EVENT, that, false);
		that.element.removeEventListener(END_EVENT, that, false);
		that.element.removeEventListener('DOMSubtreeModified', that, false);
		that.element.removeEventListener('click', that, true);
		document.removeEventListener('webkitTransitionEnd', that, false);

		if (that.scrollBarX) {
			that.scrollBarX = that.scrollBarX.remove();
		}

		if (that.scrollBarY) {
			that.scrollBarY = that.scrollBarY.remove();
		}
		
		if (full) {
			that.wrapper.parentNode.removeChild(that.wrapper);
		}
		
		return null;
	}
};

function scrollbar (dir, wrapper, fade, shrink) {
	var that = this, style;
	
	that.dir = dir;
	that.fade = fade;
	that.shrink = shrink;
	that.uid = ++uid;

	// Create main scrollbar
	that.bar = document.createElement('div');

	style = 'position:absolute;top:0;left:0;-webkit-transition-timing-function:cubic-bezier(0,0,0.25,1);pointer-events:none;-webkit-transition-duration:0;-webkit-transition-delay:0;-webkit-transition-property:-webkit-transform;z-index:10;background:rgba(0,0,0,0.5);' +
		'-webkit-transform:' + translateOpen + '0,0' + translateClose + ';' +
		(dir == 'horizontal' ? '-webkit-border-radius:3px 2px;min-width:6px;min-height:5px' : '-webkit-border-radius:2px 3px;min-width:5px;min-height:6px');

	that.bar.setAttribute('style', style);

	// Create scrollbar wrapper
	that.wrapper = document.createElement('div');
	style = '-webkit-mask:-webkit-canvas(scrollbar' + that.uid + that.dir + ');position:absolute;z-index:10;pointer-events:none;overflow:hidden;opacity:0;-webkit-transition-duration:' + (fade ? '300ms' : '0') + ';-webkit-transition-delay:0;-webkit-transition-property:opacity;' +
		(that.dir == 'horizontal' ? 'bottom:2px;left:1px;right:7px;height:5px' : 'top:1px;right:2px;bottom:7px;width:5px;');
	that.wrapper.setAttribute('style', style);

	// Add scrollbar to the DOM
	that.wrapper.appendChild(that.bar);
	wrapper.appendChild(that.wrapper);
}

scrollbar.prototype = {
	init: function (scroll, size) {
		var that = this,
			ctx, sbSize;

		// Create scrollbar mask
		if (that.dir == 'horizontal') {
			sbSize = that.wrapper.offsetWidth;
			ctx = document.getCSSCanvasContext("2d", "scrollbar" + that.uid + that.dir, sbSize, 5);
			ctx.fillStyle = "rgb(0,0,0)";
			ctx.beginPath();
			ctx.arc(2.5, 2.5, 2.5, Math.PI/2, -Math.PI/2, false);
			ctx.lineTo(sbSize-2.5, 0);
			ctx.arc(sbSize-2.5, 2.5, 2.5, -Math.PI/2, Math.PI/2, false);
			ctx.closePath();
			ctx.fill();
		} else {
			sbSize = that.wrapper.offsetHeight;
			ctx = document.getCSSCanvasContext("2d", "scrollbar" + that.uid + that.dir, 5, sbSize);
			ctx.fillStyle = "rgb(0,0,0)";
			ctx.beginPath();
			ctx.arc(2.5, 2.5, 2.5, Math.PI, 0, false);
			ctx.lineTo(5, sbSize-2.5);
			ctx.arc(2.5, sbSize-2.5, 2.5, 0, Math.PI, false);
			ctx.closePath();
			ctx.fill();
		}

		that.maxSize = that.dir == 'horizontal' ? that.wrapper.clientWidth : that.wrapper.clientHeight;
		that.size = Math.round(that.maxSize * that.maxSize / size);
		that.maxScroll = that.maxSize - that.size;
		that.toWrapperProp = that.maxScroll / (scroll - size);
		that.bar.style[that.dir == 'horizontal' ? 'width' : 'height'] = that.size + 'px';
	},
	
	setPosition: function (pos, hidden) {
		var that = this;
		
		if (!hidden && that.wrapper.style.opacity != '1') {
			that.show();
		}

		pos = that.toWrapperProp * pos;
		
		if (pos < 0) {
			pos = that.shrink ? pos + pos*3 : 0;
			if (that.size + pos < 5) {
				pos = -that.size+5;
			}
		} else if (pos > that.maxScroll) {
			pos = that.shrink ? pos + (pos-that.maxScroll)*3 : that.maxScroll;
			if (that.size + that.maxScroll - pos < 5) {
				pos = that.size + that.maxScroll - 5;
			}
		}

		pos = that.dir == 'horizontal'
			? translateOpen + Math.round(pos) + 'px,0' + translateClose
			: translateOpen + '0,' + Math.round(pos) + 'px' + translateClose;

		setWebkitTransform(that.bar, pos);
	},

	show: function () {
		if (has3d) {
			this.wrapper.style.webkitTransitionDelay = '0';
		}
		this.wrapper.style.opacity = '1';
	},

	hide: function () {
		if (has3d) {
			this.wrapper.style.webkitTransitionDelay = '200ms';
		}
		this.wrapper.style.opacity = '0';
	},
	
	remove: function () {
		this.wrapper.parentNode.removeChild(this.wrapper);
		return null;
	}
};

// Is translate3d compatible?
var has3d = ('WebKitCSSMatrix' in window && 'm11' in new WebKitCSSMatrix()),
	// Device sniffing
	isIphone = (/iphone/gi).test(navigator.appVersion),
	isIpad = (/ipad/gi).test(navigator.appVersion),
	isAndroid = (/android/gi).test(navigator.appVersion),
	isTouch = isIphone || isIpad || isAndroid,
	// Event sniffing
	START_EVENT = isTouch ? 'touchstart' : 'mousedown',
	MOVE_EVENT = isTouch ? 'touchmove' : 'mousemove',
	END_EVENT = isTouch ? 'touchend' : 'mouseup',
	// Translate3d helper
	translateOpen = 'translate' + (has3d ? '3d(' : '('),
	translateClose = has3d ? ',0)' : ')',
	// Unique ID
	uid = 0;

// Expose iScroll to the world
window.iScroll = iScroll;
})();