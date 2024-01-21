<script>
	import { onMount } from "svelte";
	import { Client } from "@stomp/stompjs";
	let stompClient;
	let text = "";
	let messageLog = [];

	onMount(() => {
		const URL = "ws://localhost:8080/connect";

		stompClient = new Client({
			brokerURL: URL,
			onConnect: () => {
				console.log("connection success");
				stompClient.subscribe("/topic/res", (message) => {
					console.log("Received: " + message);
					console.log("body value : " + message.body);

					messageLog.push(JSON.parse(message.body).value);
					messageLog = messageLog;
				});
			},
		});
		stompClient.activate();
	});

	function publish() {
		console.log("전송 성공!");
		stompClient.publish({
			destination: "/pub/hello",
			body: JSON.stringify({value: text})
		});
	}
</script>

<input type="text" bind:value={text} />

<button on:click={publish}>전송!</button>

<h2>messages</h2>
{#each messageLog as m, i}
	{i} : {m} <br>
{/each}
