__author__ = 'viva'
import boto
import os
import sys
from boto.ec2.regioninfo import RegionInfo
from boto.ec2.securitygroup import SecurityGroup


region = RegionInfo(name='melbourne', endpoint='nova.rc.nectar.org.au')

# team ec2 credentials

ec2_conn = boto.connect_ec2(aws_access_key_id='674dc5da364d44a1b82289bca592903c',
                            aws_secret_access_key='d353c780214d43aab0156157a73027e4',
                            is_secure=True, region=region, port=8773,
                            path='/services/Cloud', validate_certs=False)

groups = ec2_conn.get_all_security_groups()
names = [group.name for group in groups]

# create security group
if 'HTTP' not in names:
    http = ec2_conn.create_security_group('HTTP', 'Access HTTP and HTTPS')
    http.authorize('tcp', 80, 80, '0.0.0.0/0')
    http.authorize('tcp', 443, 443, '0.0.0.0/0')

if 'SSH' not in names:
    ssh = ec2_conn.create_security_group('SSH', 'Access SSH')
    ssh.authorize('tcp', 22, 22, '0.0.0.0/0')

if 'CouchDB' not in names:
    couchdb = ec2_conn.create_security_group('CouchDB', 'Access CouchDB')
    couchdb.authorize('tcp', 5984, 5984, '0.0.0.0/0')


'''
# just run for the first time
# create and save a new key pair
if ec2_conn.get_key_pair('sandiego'):
    ec2_conn.delete_key_pair('sandiego')

sandiego = ec2_conn.create_key_pair('sandiego')
if os.path.exists('sandiego.pem'):
    os.remove(sys.path[0] + '/' + 'sandiego.pem')
else:
    sandiego.save(sys.path[0])
'''

'''
# run if necessary
images = ec2_conn.get_all_images()
for img in images:
    if 'Ubuntu 14.04' in img.name:
        print img.id, img.name
'''


# create an instance
ec2_conn.run_instances('ami-000022b3', key_name='mykey', instance_type='m1.small',
                       security_groups=['HTTP', 'SSH', 'CouchDB'], placement='melbourne-np')


reservations = ec2_conn.get_all_reservations()
instIDs = []
for idx, res in enumerate(reservations):
    instIDs.append(res.instances[0].id)
    print res.id, res.instances[0]
print instIDs


'''
# run if necessary
# create and attach volume
ec2_conn.create_volume(50, 'melbourne-np')
vols = ec2_conn.get_all_volumes()
print vols
ec2_conn.attach_volume('', '', '/dev/vdc')
'''

# run if necessary
# terminate instances
# ec2_conn.terminate_instances(instance_ids=instIDs)