	def end_to_end_test():
		url = 'http://cluster-address.local/query?q=test'
		r = requests.get(url)
		assert r.json()['content'] == 'hello world test'
