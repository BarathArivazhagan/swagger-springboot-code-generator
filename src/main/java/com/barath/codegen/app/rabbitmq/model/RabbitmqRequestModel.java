package com.barath.codegen.app.rabbitmq.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RabbitmqRequestModel {
	
	private Map<String,String> properties;
	
	private List<Queue> queues;
	
	private List<Exchange> exchanges;
	
	private List<Binding> bindings;	
	
	
	
	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public List<Queue> getQueues() {
		return queues;
	}

	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}

	public List<Exchange> getExchanges() {
		return exchanges;
	}

	public void setExchanges(List<Exchange> exchanges) {
		this.exchanges = exchanges;
	}

	public List<Binding> getBindings() {
		return bindings;
	}

	public void setBindings(List<Binding> bindings) {
		this.bindings = bindings;
	}

	protected static class Queue{
		
		private String name;
		
		private boolean durable;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
			
		public boolean isDurable() {
			return durable;
		}

		public void setDurable(boolean durable) {
			this.durable = durable;
		}

		public Queue(String name, boolean durable) {
			super();
			this.name = name;
			this.durable = durable;
		}
		
		
		
	}
	
	protected static class Exchange{
		
		private String name;
		
		private String type;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Exchange() {
			super();
			
		}

		public Exchange(String name, String type) {
			super();
			this.name = name;
			this.type = type;
		}
		
		
		
		
	}
	
	protected static class Binding{
		
			private String name;
			
			private String exchangeName;
			
			private String queueName;
			
			private String routingKey;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getExchangeName() {
				return exchangeName;
			}

			public void setExchangeName(String exchangeName) {
				this.exchangeName = exchangeName;
			}

			public String getQueueName() {
				return queueName;
			}

			public void setQueueName(String queueName) {
				this.queueName = queueName;
			}

			public String getRoutingKey() {
				return routingKey;
			}

			public void setRoutingKey(String routingKey) {
				this.routingKey = routingKey;
			}

			public Binding() {
				super();
				
			}

			public Binding(String name, String exchangeName, String queueName, String routingKey) {
				super();
				this.name = name;
				this.exchangeName = exchangeName;
				this.queueName = queueName;
				this.routingKey = routingKey;
			}
			
			
			
	}

}
