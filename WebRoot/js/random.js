function getRandPassword(min, max) {
	var source = 'abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789', 
	length = Math.ceil(Math.random() * (max - min) + min), password = '';
	for ( var i = 0; i < length; i++) {
		password += source.charAt(Math.ceil(Math.random() * 1000)
				% source.length);
	}
	return password;
}