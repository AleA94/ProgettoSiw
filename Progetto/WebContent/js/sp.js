$(document).ready(function()
{
$("#slider").slider(
{
	animate : true,
	value : 1,
	min : 0,
	max : 1000,
	step : 10,
	slide : function(event, ui)
	{

	update(1, ui.value); // changed
	}
});

$("#slider2").slider(
{
	animate : true,
	value : 0,
	min : 0,
	max : 500,
	step : 1,
	slide : function(event, ui)
	{

	update(2, ui.value); // changed
	}
});

// Added, set initial value.
$("#amount").val(0);
$("#duration").val(0);
$("#amount-label").text(0);
$("#duration-label").text(0);

update();

});

// changed. now with parameter
function update(slider, val)
{
// changed. Now, directly take value from ui.value. if not set (initial,
// will use current value.)
var $amount = slider == 1 ? val : $("#amount").val();
var $duration = slider == 2 ? val : $("#duration").val();

/*
 * commented $amount = $( "#slider" ).slider( "value" ); $duration = $(
 * "#slider2" ).slider( "value" );
 */

$total = "$" + ($amount * $duration);
$("#amount").val($amount);
$("#amount-label").text($amount);
$("#duration").val($duration);
$("#duration-label").text($duration);
$("#total").val($total);
$("#total-label").text($total);

$('#slider a').html(
		'<label><span class="glyphicon glyphicon-chevron-left"></span> ' + $amount
				+ ' <span class="glyphicon glyphicon-chevron-right"></span></label>');
$('#slider2 a').html(
		'<label><span class="glyphicon glyphicon-chevron-left"></span> ' + $duration
				+ ' <span class="glyphicon glyphicon-chevron-right"></span></label>');

}
(function($)
{

// Detect touch support
$.support.touch = 'ontouchend' in document;

// Ignore browsers without touch support
if (!$.support.touch)
{
	return;

}

var mouseProto = $.ui.mouse.prototype, _mouseInit = mouseProto._mouseInit, _mouseDestroy = mouseProto._mouseDestroy, touchHandled;

/**
 * Simulate a mouse event based on a corresponding touch event
 * 
 * @param {Object}
 *            event A touch event
 * @param {String}
 *            simulatedType The corresponding mouse event
 */
function simulateMouseEvent(event, simulatedType)
{

// Ignore multi-touch events
if (event.originalEvent.touches.length > 1)
{
	return;

}

event.preventDefault();

var touch = event.originalEvent.changedTouches[0], simulatedEvent = document
		.createEvent('MouseEvents');

// Initialize the simulated mouse event using the touch event's
// coordinates
simulatedEvent.initMouseEvent(simulatedType, // type
true, // bubbles
true, // cancelable
window, // view
1, // detail
touch.screenX, // screenX
touch.screenY, // screenY
touch.clientX, // clientX
touch.clientY, // clientY
false, // ctrlKey
false, // altKey
false, // shiftKey
false, // metaKey
0, // button
null // relatedTarget
);

// Dispatch the simulated event to the target element
event.target.dispatchEvent(simulatedEvent);

}

/**
 * Handle the jQuery UI widget's touchstart events
 * 
 * @param {Object}
 *            event The widget element's touchstart event
 */
mouseProto._touchStart = function(event)
{

var self = this;

// Ignore the event if another widget is already being handled
if (touchHandled || !self._mouseCapture(event.originalEvent.changedTouches[0]))
{
	return;

}

// Set the flag to prevent other widgets from inheriting the touch event
touchHandled = true;

// Track movement to determine if interaction was a click
self._touchMoved = false;

// Simulate the mouseover event
simulateMouseEvent(event, 'mouseover');

// Simulate the mousemove event
simulateMouseEvent(event, 'mousemove');

// Simulate the mousedown event
simulateMouseEvent(event, 'mousedown');

};

/**
 * Handle the jQuery UI widget's touchmove events
 * 
 * @param {Object}
 *            event The document's touchmove event
 */
mouseProto._touchMove = function(event)
{

// Ignore event if not handled
if (!touchHandled)
{
	return;

}

// Interaction was not a click
this._touchMoved = true;

// Simulate the mousemove event
simulateMouseEvent(event, 'mousemove');

};

/**
 * Handle the jQuery UI widget's touchend events
 * 
 * @param {Object}
 *            event The document's touchend event
 */
mouseProto._touchEnd = function(event)
{

// Ignore event if not handled
if (!touchHandled)
{
	return;

}

// Simulate the mouseup event
simulateMouseEvent(event, 'mouseup');

// Simulate the mouseout event
simulateMouseEvent(event, 'mouseout');

// If the touch interaction did not move, it should trigger a click
if (!this._touchMoved)
{

	// Simulate the click event
	simulateMouseEvent(event, 'click');

}

// Unset the flag to allow other widgets to inherit the touch event
touchHandled = false;
};

/**
 * A duck punch of the $.ui.mouse _mouseInit method to support touch nts. This
 * method extends the widget with bound touch event n ers that translate touch
 * events to mouse events and pass them to e w get's original mouse event
 * handling methods.
 */

us
roto._mouseInit = function()
{

a

lf = this;

// egate the touch handlers to the widget's element elf
lement.bind(
{
	utart : $.poxy(self, '_touchStart'),
	touove : $.poxy(self, '_touchMove'),
	tound : $.poxy(self, '_touchEnd')
});

// l the original $.ui.mouse init method_mou
Init.call(self);
};

/*
 * 
 * R ve the touch event handlers
 */

us
roto._mouseDestroy = function()
{

a

lf = this;

// egate the touch handlers to the widget's element self
lement.unbind(
{
	utart : $.poxy(self, '_touchStart'),
	touove : $.poxy(self, '_touchMove'),
	tound : $.poxy(self, '_touchEnd')
});

// l the original $.ui.mouse destroy method_mou
Destroy.call(self);
};

})

(query);
